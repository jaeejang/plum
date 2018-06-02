package org.plum.dao.advice;

import java.util.List;

import org.plum.model.advice.AdviceBranch;

public interface AdviceBranchMapper {
    List<AdviceBranch> selectAll();
}