# 


**简介**:


**HOST**:localhost:8081


**联系人**:yangxiaobai.top


**Version**:1.0


**接口路径**:/v2/api-docs?group=武理哔哔机


[TOC]






# 一级评论相关接口


## 新增评论


**接口地址**:`/firstComment/create`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>一级评论，评论内容长度小于等于255</p>



**请求示例**:


```javascript
{
  "firstCommentArticleId": 0,
  "firstCommentContent": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|firstcommentDTO|评论对象，包括所属文章id，和评论内容|body|true|FirstcommentDTO|FirstcommentDTO|
|&emsp;&emsp;firstCommentArticleId|||false|integer(int32)||
|&emsp;&emsp;firstCommentContent|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 根据点赞数查询文章评论


**接口地址**:`/firstComment/getHotList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>默认查询最热的十条数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|articleId|评论所属文章id|query|true|ref||
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 根据发布时间查询文章评论


**接口地址**:`/firstComment/getNewList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>默认查询最新的十条数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|articleId|评论所属文章id|query|true|ref||
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 给评论点赞或取消


**接口地址**:`/firstComment/like/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|一级评论id|path|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


# 二级评论相关接口


## 新增二级评论


**接口地址**:`/sonComment/create`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>二级评论，评论内容长度小于等于255</p>



**请求示例**:


```javascript
{
  "firstCommentArticleId": 0,
  "firstCommentContent": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|soncommentDTO|评论对象，包括所属文章id，和评论内容|body|true|FirstcommentDTO|FirstcommentDTO|
|&emsp;&emsp;firstCommentArticleId|||false|integer(int32)||
|&emsp;&emsp;firstCommentContent|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 查询父级评论对应的子级评论


**接口地址**:`/sonComment/getList`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>默认查询最新的十条数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|firstCommentId|父级评论id|query|true|ref||
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 给二级评论点赞或取消


**接口地址**:`/sonComment/like/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|二级评论id|path|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


# 关注相关接口


## 查询是否已经关注对方用户


**接口地址**:`/follow/or/not/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|一级评论id|path|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 关注或者取关目标用户


**接口地址**:`/follow/{userId}/{isFollow}`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>如果是取消关注，isFollow参数传入false，如果是关注，isFollow传入true</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|isFollow|是否关注|path|true|boolean||
|userId|目标用户id|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


# 文章分类相关接口


## 查询分类列表


**接口地址**:`/category`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 通过id查询分类


**接口地址**:`/category/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


# 文章相关接口


## 查询某用户的所有文章


**接口地址**:`/article/all`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|useId|用户id|query|true|string||
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 发布文章


**接口地址**:`/article/create`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>返回文章的id，凭此id上传图片</p>



**请求示例**:


```javascript
{
  "articleCategoryId": 0,
  "articleContent": "",
  "articleTitle": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|articleDTO|新增的文章对象|body|true|ArticleDTO|ArticleDTO|
|&emsp;&emsp;articleCategoryId|||false|integer(int32)||
|&emsp;&emsp;articleContent|||false|string||
|&emsp;&emsp;articleTitle|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 查询文章详细信息


**接口地址**:`/article/detail/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|文章id|path|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 过去一个月点赞数最多的文章


**接口地址**:`/article/hot`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>默认查询点赞数最多的十条数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 给文章点赞或取消


**接口地址**:`/article/like/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|文章id|path|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 首页最新文章


**接口地址**:`/article/new`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>默认查询最新的十条数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|current|查询的页码数|query|false|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 上传文章图片


**接口地址**:`/article/uploadImg`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>最多上传9张图片，大小限制10m以内</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|articleId|文章id|query|true|ref||
|files|文件列表|formData|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


# 用户相关接口


## 注册用户


**接口地址**:`/user/create`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户密码长度必须是大于6，只允许出现数字和字母</p>



**请求示例**:


```javascript
{
  "code": "",
  "email": "",
  "password": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|loginFormDTO|用户注册对象|body|true|LoginFormDTO|LoginFormDTO|
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;password|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 编辑用户信息


**接口地址**:`/user/edit`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>需要传入nickname(昵称),sex(性别)</p>



**请求示例**:


```javascript
{
  "avatar": "",
  "createTime": "",
  "nickname": "",
  "password": "",
  "salt": "",
  "sex": 0,
  "userId": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|user|body|true|User|User|
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;salt|||false|string||
|&emsp;&emsp;sex|||false|integer(int32)||
|&emsp;&emsp;userId|||false|string||
|User|用户User对象||false|User|User|
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;salt|||false|string||
|&emsp;&emsp;sex|||false|integer(int32)||
|&emsp;&emsp;userId|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 修改密码


**接口地址**:`/user/editPassword`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户密码长度必须是大于6，只允许出现数字和字母</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|newPassword|新密码|query|true|string||
|oldPassword|原密码|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 用户登录


**接口地址**:`/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "code": "",
  "email": "",
  "password": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|loginForm|用户登录对象|body|true|LoginFormDTO|LoginFormDTO|
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;password|||false|string||
|session|session对象||true|HttpSession|HttpSession|
|creationTime||query|false|integer(int64)||
|id||query|false|string||
|lastAccessedTime||query|false|integer(int64)||
|maxInactiveInterval||query|false|integer(int32)||
|new||query|false|boolean||
|servletContext.classLoader||query|false|ref||
|servletContext.contextPath||query|false|string||
|servletContext.defaultSessionTrackingModes|枚举类型,可用值:COOKIE,URL,SSL|query|false|array|string|
|servletContext.effectiveMajorVersion||query|false|integer(int32)||
|servletContext.effectiveMinorVersion||query|false|integer(int32)||
|servletContext.effectiveSessionTrackingModes|枚举类型,可用值:COOKIE,URL,SSL|query|false|array|string|
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas||query|false|array|string|
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes||query|false|array|string|
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces||query|false|string||
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns||query|false|array|string|
|servletContext.jspConfigDescriptor.taglibs[0].taglibLocation||query|false|string||
|servletContext.jspConfigDescriptor.taglibs[0].taglibURI||query|false|string||
|servletContext.majorVersion||query|false|integer(int32)||
|servletContext.minorVersion||query|false|integer(int32)||
|servletContext.requestCharacterEncoding||query|false|string||
|servletContext.responseCharacterEncoding||query|false|string||
|servletContext.serverInfo||query|false|string||
|servletContext.servletContextName||query|false|string||
|servletContext.sessionCookieConfig.comment||query|false|string||
|servletContext.sessionCookieConfig.domain||query|false|string||
|servletContext.sessionCookieConfig.httpOnly||query|false|boolean||
|servletContext.sessionCookieConfig.maxAge||query|false|integer(int32)||
|servletContext.sessionCookieConfig.name||query|false|string||
|servletContext.sessionCookieConfig.path||query|false|string||
|servletContext.sessionCookieConfig.secure||query|false|boolean||
|servletContext.sessionTimeout||query|false|integer(int32)||
|servletContext.virtualServerName||query|false|string||
|valueNames||query|false|array|string|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 退出登录


**接口地址**:`/user/logout`


**请求方式**:`PUT`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>不需传入参数，代token</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 通过id查用户信息


**接口地址**:`/user/quary/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>传入id（即email），返回头像地址，nickname等基本信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|用户id|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 教育邮箱发送验证码


**接口地址**:`/user/sentCode`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>给教育邮箱发送验证码，前端验证一下邮箱:长度为18位并且以@whut.edu.cn结尾</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|email|教育邮箱地址|query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```


## 上传头像


**接口地址**:`/user/uploadAvatar`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:<p>需要前端做校验，图片后缀，大小（暂定不超过1m）</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|图片对象|formData|true|ref||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|errorMsg||string||
|success||boolean||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"errorMsg": "",
	"success": true
}
```