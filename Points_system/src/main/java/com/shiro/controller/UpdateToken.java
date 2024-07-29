package com.shiro.controller;

import com.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("myController")
public class UpdateToken {
    @Autowired
    private UserService userService;

    public String updateToken(
            @RequestParam("name") String name,
            @RequestParam("paper_nice") String paperNice,
            @RequestParam("platform_nice") String platformNice,
            @RequestParam("system_nice") String systemNice,
            @RequestParam("paper_low") String paperLow,
            @RequestParam("platform_low") String platformLow,
            @RequestParam("system_low") String systemLow,
            @RequestParam("paper_middle") String paperMiddle,
            @RequestParam("platform_middle") String platformMiddle,
            @RequestParam("system_middle") String systemMiddle,
            @RequestParam("paper_high") String paperHigh,
            @RequestParam("platform_high") String platformHigh,
            @RequestParam("system_high") String systemHigh,
            @RequestParam("professor_rewards") String professorRewards,
            @RequestParam("internal_adjustments") String internalAdjustments,
            Model model
    ) {
        try {
            int nice_to_have = Integer.parseInt(paperNice) + Integer.parseInt(platformNice) + Integer.parseInt(systemNice);
            int low = Integer.parseInt(paperLow) + Integer.parseInt(platformLow) + Integer.parseInt(systemLow);
            int middle = Integer.parseInt(paperMiddle) + Integer.parseInt(platformMiddle) + Integer.parseInt(systemMiddle);
            int high = Integer.parseInt(paperHigh) + Integer.parseInt(platformHigh) + Integer.parseInt(systemHigh);
            int count = nice_to_have * 10 + low * 20 + middle * 50 + high * 100 + Integer.parseInt(professorRewards) + Integer.parseInt(internalAdjustments);
            int professor_rewards = Integer.parseInt(professorRewards);

            int key = userService.Update_token(name, nice_to_have, low, middle, high, professor_rewards, count);

            if (key > 0) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "失败");
            }
        } catch (Exception e) {
            model.addAttribute("result", "失败");
        }

        return "Recorded_Points"; // 请替换为你的视图名称
    }
}
