package com.yxz.wulibibiji.controller;

import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Message;
import com.yxz.wulibibiji.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangxiaozhuo
 * @date 2023/01/13
 */
@Api(tags = "消息相关接口")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @ApiImplicitParam(name = "Message", value = "消息对象，只需要包含toId 和 content 即可", dataType = "Message", required = true)
    @ApiOperation(value = "发送消息", notes = "消息内容小于等于255，返回code如果200，表示发送成功，500表示发送失败，弹窗显示")
    @PostMapping("/send")
    public Result sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message.getToId(), message.getContent());
    }

    @ApiImplicitParam(name = "messageId", value = "消息id", dataType = "Integer", required = true)
    @ApiOperation(value = "撤回消息", notes = "两分钟以内的消息可以撤回")
    @PostMapping("/withdraw")
    public Result withdrawMessage(@RequestParam(value = "messageId") Integer id) {
        return messageService.withdrawMessage(id);
    }

    @ApiOperation(value = "查询某用户有多少未读信息", notes = "不需要参数，此接口查的是所有的未读消息（包括私信和系统通知）")
    @GetMapping("/unreadAll")
    public Result unreadAllMessage() {
        return messageService.unreadAllMessage();
    }

    @ApiOperation(value = "查询某用户有多少未读私信", notes = "不需要参数，此接口查的只有未读的私信条数")
    @GetMapping("/unreadPrivateMessage")
    public Result unreadPrivateMessage() {
        return messageService.unreadPrivateMessage();
    }

    @ApiOperation(value = "查询某用户有多少未读系统通知", notes = "不需要参数，此接口查的只有未读的私信条数")
    @GetMapping("/unreadSystemMessage")
    public Result unreadSystemMessage() {
        return messageService.unreadSystemMessage();
    }

    @ApiImplicitParam(name = "topic", value = "分类名称", dataType = "String", required = true)
    @ApiOperation(value = "查询某个分类下下最新的通知", notes = "分类包括，like（点赞），comment（评论），follow（关注）")
    @GetMapping("/getLatestNotice/{topic}")
    public Result getLatestNotice(@PathVariable("topic") String topic) {
        return messageService.getLatestNotice(topic);
    }

    @ApiImplicitParam(name = "topic", value = "分类名称", dataType = "String", required = true)
    @ApiOperation(value = "查询某个分类下下未读的通知的数量", notes = "分类包括，like（点赞），comment（评论），follow（关注）")
    @GetMapping("/getNoticeUnreadCount/{topic}")
    public Result getNoticeUnreadCount(@PathVariable("topic") String topic) {
        return messageService.getNoticeUnreadCount(topic);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "分类名称", dataType = "String", required = true),
            @ApiImplicitParam(name = "current", value = "分页", dataType = "Integer", required = false)
    })
    @ApiOperation(value = "查询某个分类所包含的通知列表", notes = "分类包括，like（点赞），comment（评论），follow（关注）")
    @GetMapping("/getAllNotice")
    public Result getAllNotice(@RequestParam("topic") String topic, @RequestParam(value = "current", defaultValue = "1") Integer current) {
        return messageService.getAllNotice(topic, current);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "对方用户id", dataType = "String", required = true),
            @ApiImplicitParam(name = "current", value = "查询的消息范围", dataType = "Integer", required = false)
    })
    @ApiOperation(value = "查询和用户userId的聊天记录", notes = "默认展示最新的5条，上拉刷新")
    @GetMapping("/getMessage")
    public Result getMessage(@RequestParam(value = "userId") String userId,
                             @RequestParam(value = "currend", defaultValue = "1") Integer current) {
        return messageService.getMessage(userId, current);
    }


    @ApiImplicitParam(name = "current", value = "查询条数的范围，默认是最近十条，current=1表示查询第11到20条", dataType = "Integer", required = false)
    @ApiOperation(value = "查询聊天列表接口", notes = "查询用户的所有消息的第一条,并且按照时间排序")
    @GetMapping("/getFirstList")
    public Result getFirstList(@RequestParam(value = "current", defaultValue = "1") Integer current) {
        return messageService.getFirstList(current);
    }

    @ApiImplicitParam(name = "userId", value = "对方用户id", dataType = "String", required = true)
    @ApiOperation(value = "查询和用户a之间有多少未读消息")
    @GetMapping("/getUnreadCount")
    public Result getUnreadCount(@RequestParam(value = "userId") String userId) {
        return messageService.getUnreadCount(userId);
    }

}
