package com.spinetracker.spinetracker.domain.board.command.domain.service;

import com.spinetracker.spinetracker.domain.board.command.infra.response.CoupangOpenGraphResponse;

public interface RequestProductInfo {

    CoupangOpenGraphResponse getCoupangInfo(String productUrl);
}
