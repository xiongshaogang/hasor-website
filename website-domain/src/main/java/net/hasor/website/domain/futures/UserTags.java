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
package net.hasor.website.domain.futures;
/**
 * 用户扩展信息数据结构
 * @version : 2017年02月08日
 * @author 赵永春 (zyc@hasor.net)
 */
public class UserTags {
    private boolean admin      = false;
    private boolean newProject = false;
    //
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public boolean isNewProject() {
        return newProject;
    }
    public void setNewProject(boolean newProject) {
        this.newProject = newProject;
    }
}
