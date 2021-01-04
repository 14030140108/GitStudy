## usercenter(8001)
- `/user/login` 用户登录
- `/user/verifyCode` 发送短信验证码
- `/user/loginBySms` 手机验证码登录
- `/user/findPwdBySms` 通过手机验证码修改密码
- `/user/phoneVerification` 验证手机

- `/user` 用户注册
- `/user` put更新用户信息
- `/user/{id}` 根据用户ID查询用户信息
- `/user/{id}` 根据用户ID删除用户
- `/user/Authentication` 根据用户ID查询是否认证
- `/user/account/repetition` 查询用户名是否存在
- `/user/phone/repetition` 查询手机是否已注册

- `/AuthenticatedUser` 注册用户信息
- `/AuthenticatedUser` put 更新用户信息
- `/AuthenticatedUser/{userid}` 查询认证用户信息
- `/AuthenticatedUser/idCardPhoto` 上传身份证照片

**对外API**
1. `user/api` 用户insert student(手动录入学生信息)
2. `AuthUserDao.queryById` (course)
3. `AuthUserDao.getAll` (course)
4. `/AuthenticatedUser/{userid}` 查询认证用户信息(course)

---
## msm(8002)
- `send/{phone}` 调第三方接口发送短信验证码（有效期redies）


---
## course(8003)
- `/course/{orgId}` 增加课程信息
- `/course/{orgId}` put 修改课程信息
- `/course/{orgId}/{courseId}` 刪除机构课程信息
- `/course/organization/{orgId}` 关键字模糊查询课程信息
- `/course/getQueryCourse` 根据条件查询课程
- `/course/queryCourse/{courseId}` 根据id查询课程
- `/course/{orgId}` 获取机构的所有课程 (使用Auth.getAll)
- `/course/organization/{orgId}/basicInfo` 课程信息模糊查询的前置信息(但是此处没有设计好，前置信息依赖teacher（—>user、Auth)）、courseCategory
- `/course/{orgId}/basicInfo` 添加课程时的基础信息

- `/coursecategory` 获取课程类别树
- `/coursecategory/queryAllCourseCategoryIdAndName` 获取所有课程类别的ID和Name

- `/course/syllabus` 课表生成(不同角色获取到不同课表)

- `/course/{orgId}/option/student/{stuId}` 获取已经开始且该学生尚未选修过的课程==简短信息
- `/course/{courseId}/student` 获取课程的选修名单
- `/course/{courseId}/trialstudent` 获取课程的试听名单

- `/student/{userId}/course/{courseId}/sign`课程签到
- `/course/{courseId}/signIn/roster` 课程签到名单
- `/course/history` 课程的上课历史

- `/course/{courseId}/student` 获取课程的选修名单
- `/course/{courseId}/trialstudent` 获取课程的试听名单）

 **对外API**
 1. `/course/organization/{orgId} "关键字模糊查询课程信息` course-order模块引用（获取已经开始且该学生尚未选修过的课程==简短信息）
 2. `/coursecategory/{categoryId}` Student模块引用(通过id获取学生信息)


- courseDao中添加了userDao和AuthDao,genderHandler报错，找不到class,改为了mybitis中自带枚举转换

----
## Bind(8004)
- `/teacher/{orgId}/unchecked` 按机构id获取全部未审核教师
- `/organization/teacher/confirmation` 确认教师挂靠请求
- `/teacher/organization` 根据id查询教师是否挂靠机构
- `/teacher/organization/{orgId}` 教师申请挂靠机构
- `/teacher/process` 教师审核进度查询
- `/teacher/organization/{teaId}`  delete 教师申请解除挂靠

- `/worker/{userId}/isBind/organization` 查询员工是否绑定机构
- `/worker/process` 查看员工挂靠进度
- `/organization/worker/confirmation` 管理员确认员工挂靠请求

----



---
## Organization(8006)
- `/organization` 机构信息录入
- `/organization` put机构信息修改
- `/organization/{org_id}` 获取机构的详细信息

**对外API**
1. `/organization/api/{org_id}` (queryById 对外 student、course)
---

## Student(8007)
- `/student/{stuId}` 通过id获取学生信息 （course）
- `/student/preInfo/{user_id}` 获取学生前置信息
- `/student/{org_id}/manual` 手动录入学生信息(user)
- `/student/organizations/{user_id}` 获取该学生挂靠的全部机构(organization)
- `/student/organization/{org_id}/{user_id}` 学生向机构报名
- `/student/organization/{org_id}/{user_id}` delete 学生解除挂靠某机构


## Teacher（8008）

---
## Payment(8009)
- `/transaction/payAddr/{orderId}`
---
## Euraka(7001)
- `作用 在使用 RestTemplate 的时候 如果 RestTemplate 上面有 这个注解，那么 这个 RestTemplate 调用的 远程地址，会走负载均衡器。`
- 已注册
    - usercenter(8001)
    - msm(8002)
    
## 已解决
1. `handlers` 每次gender枚举映射出错。
    原因：将自定义handlers写在`users`模块，虽然idea可以ctrl+点击找到，但找到的是`user`模块中的，所有报找不到类
    解决：当多个模块使用自定义枚举handler，将自定义handler抽取到`common-api`模块

   
  
## 项目构架问题
1. 是否将用户身份认证模块从 usercenter微服务中抽离（目前在一个服务中）

## 疑问
1. 抽离common-api包，是不是每个引用该依赖的模块都会将该依赖加入到自己打包后的项目内

## 问王哥
1. users.addRole(全部在user表中处理)