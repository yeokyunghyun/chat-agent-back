package com.example.chat_agent_back.domain.inquiry.service;

import com.example.chat_agent_back.domain.inquiry.dto.response.InquiryTypeTreeResponse;
import com.example.chat_agent_back.domain.inquiry.entity.ChatInquiry;
import com.example.chat_agent_back.domain.inquiry.repository.InquiryMainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryMainServiceImpl implements InquiryMainService{

    private final InquiryMainRepository inquiryMainRepository;

    @Override
    public List<InquiryTypeTreeResponse> getInquiryTypeTree() {
        List<ChatInquiry> all = inquiryMainRepository.findAll();

        // parentId 기준 그룹핑
        Map<Long, List<ChatInquiry>> groupByParent =
                all.stream().collect(Collectors.groupingBy(ChatInquiry::getParentId));

        // 루트 노드 (parentId == null)
        List<ChatInquiry> roots = groupByParent.get(null);

        if (roots == null) return List.of();

        return roots.stream()
                .map(root -> buildTree(root, groupByParent))
                .toList();
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
                .label(node.getTitle())
                .children(children.isEmpty() ? null : children)
                .build();
    }

}
