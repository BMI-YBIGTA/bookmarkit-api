package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequestDto;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequestDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponseDto;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponseDto;

public interface MemberService {

    MemberResponseDto getInfo(Long id);

    MemberResponseDto modifyInfo(Long id, MemberModifyInfoRequestDto request);

    MemberResponseDto register(MemberRegisterRequestDto request);

    MemberLoginResponseDto login(MemberLoginRequestDto request);
}
