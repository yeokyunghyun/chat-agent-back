package com.example.chat_agent_back.domain.inquiry.service;

import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeDeleteRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeInsertRequest;
import com.example.chat_agent_back.domain.inquiry.dto.request.InquiryTypeUpdateRequest;
import com.example.chat_agent_back.domain.inquiry.dto.response.InquiryTypeTreeResponse;
import com.example.chat_agent_back.domain.inquiry.entity.ChatInquiry;
import com.example.chat_agent_back.domain.inquiry.repository.InquiryMainRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InquiryMainServiceImpl implements InquiryMainService{

    private final InquiryMainRepository inquiryMainRepository;
    private static final long ROOT_KEY = -1L; // 또는 -1L

    @Override
    public List<InquiryTypeTreeResponse> getInquiryTypeTree() {
        List<ChatInquiry> all = inquiryMainRepository.findAll();

        // parentId 기준 그룹핑
        Map<Long, List<ChatInquiry>> groupByParent =
                all.stream().collect(Collectors.groupingBy(ci ->
                        ci.getParentId() == null ? ROOT_KEY : ci.getParentId()
                ));

        // 루트 노드 (parentId == null)
        List<ChatInquiry> roots = groupByParent.get(-1L);

        if (roots == null) return List.of();

        return roots.stream()
                .map(root -> buildTree(root, groupByParent))
                .toList();
    }

    @Override
    @Transactional
    public void insertInquiryType(InquiryTypeInsertRequest request) {
        ChatInquiry newNode = new ChatInquiry();
        newNode.setParentId(request.getParentId());
        newNode.setRegrId(request.getUsername());
        newNode.setTitle(request.getTitle());
        newNode.setContent(request.getContent());
        newNode.setType(request.getType());

        inquiryMainRepository.save(newNode);
    }

    @Override
    @Transactional
    public void updateInquiryType(InquiryTypeUpdateRequest request) {
        ChatInquiry updateNode = inquiryMainRepository.findById(request.getId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 문의유형입니다.")
        );

        updateNode.setTitle(request.getTitle());
        updateNode.setContent(request.getContent());
        updateNode.setType(request.getType());

        inquiryMainRepository.save(updateNode);
    }

    @Override
    @Transactional
    public void deleteInquiryType(InquiryTypeDeleteRequest request) {

        Long targetId = request.getId();

        // 전체 조회
        List<ChatInquiry> all = inquiryMainRepository.findAll();

        // parentId 기준 그룹핑
        Map<Long, List<ChatInquiry>> groupByParent =
                all.stream().collect(Collectors.groupingBy(ci ->
                        ci.getParentId() == null ? ROOT_KEY : ci.getParentId()
                ));

        // 삭제할 ID들 수집
        List<Long> deleteIds = collectDeleteIds(targetId, groupByParent);

        // 삭제 실행
        inquiryMainRepository.deleteAllById(deleteIds);
    }

    private InquiryTypeTreeResponse buildTree(
            ChatInquiry node,
            Map<Long, List<ChatInquiry>> groupByParent
    ) {
        List<InquiryTypeTreeResponse> children =
                groupByParent.getOrDefault(node.getId(), List.of())
                        .stream()
                        .map(child -> buildTree(child, groupByParent))
                        .toList();

        return InquiryTypeTreeResponse.builder()
                .id(node.getId())
                .title(node.getTitle())
                .content(node.getContent())
                .type(node.getType())
                .children(children.isEmpty() ? null : children)
                .build();
    }

    private List<Long> collectDeleteIds(
            Long currentId,
            Map<Long, List<ChatInquiry>> groupByParent
    ) {
        List<Long> result = new java.util.ArrayList<>();
        result.add(currentId);

        List<ChatInquiry> children = groupByParent.get(currentId);
        if (children != null) {
            for (ChatInquiry child : children) {
                result.addAll(collectDeleteIds(child.getId(), groupByParent));
            }
        }
        return result;
    }

}
