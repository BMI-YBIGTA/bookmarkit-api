package com.bmi.bookmarkitapi.member.application.service;

import com.bmi.bookmarkitapi.member.application.model.request.MemberLoginRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberModifyInfoRequest;
import com.bmi.bookmarkitapi.member.application.model.request.MemberRegisterRequest;
import com.bmi.bookmarkitapi.member.application.model.response.MemberLoginResponse;
import com.bmi.bookmarkitapi.member.application.model.response.MemberResponse;

public interface MemberService {

    MemberResponse getInfo(Long id);

    MemberResponse modifyInfo(Long id, MemberModifyInfoRequest request);

    MemberResponse register(MemberRegisterRequest request);

    MemberLoginResponse login(MemberLoginRequest request);
}
