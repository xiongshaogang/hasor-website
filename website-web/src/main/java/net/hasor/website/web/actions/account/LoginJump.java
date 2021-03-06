/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.website.web.actions.account;
import net.hasor.core.Inject;
import net.hasor.web.Invoker;
import net.hasor.web.annotation.MappingTo;
import net.hasor.web.annotation.ReqParam;
import net.hasor.website.domain.beans.AppConstant;
import net.hasor.website.domain.beans.QuickLoginResult;
import net.hasor.website.domain.owner.SimpleOwner;
import net.hasor.website.manager.UserManager;
import net.hasor.website.web.core.Action;

import java.io.IOException;
/**
 * 登陆跳转
 * @version : 2016年1月1日
 * @author 赵永春 (zyc@hasor.net)
 */
@MappingTo("/account/loginJump.do")
public class LoginJump extends Action {
    @Inject
    private UserManager userManager;
    //
    public void execute(@ReqParam("userCode") String userCode, @ReqParam("quickCode") String quickCode, Invoker data) throws IOException {
        //
        // 登陆请求
        QuickLoginResult loginResult = this.userManager.doQuickLogin(userCode, quickCode);
        if (loginResult == null) {
            renderTo("htm", "/account/login.htm");
            return;
        }
        //
        // - 用户数据
        if (!this.isLogin()) {
            SimpleOwner owner = new SimpleOwner(loginResult.getUserDO(), loginResult.getUserDO().getNick());
            this.setSessionAttr(AppConstant.SESSION_KEY_USER, owner);
            this.setSessionAttr(AppConstant.SESSION_KEY_USER_AVATAR, loginResult.getUserDO().getAvatar());
        }
        this.setSessionAttr(AppConstant.SESSION_KEY_TARGET_USER_ID, loginResult.getUserDO().getUserID());
        this.setSessionAttr(AppConstant.SESSION_KEY_TARGET_PROVIDER, loginResult.getProvider());
        //
        // 跳转页面
        String ctx_path = data.getHttpRequest().getContextPath();
        data.getHttpResponse().sendRedirect(ctx_path + loginResult.getRedirectURL());
    }
}