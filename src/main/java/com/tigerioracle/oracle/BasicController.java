package com.tigerioracle.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BasicController {
    @Autowired
    private BasicDAO dao;


    @RequestMapping("/")
    public String viewBasicPage(Model model) {
        List<Basic> listBasic = dao.basiclist();
        List<DefaultTbs> listDefaultTbs = dao.defaulttbslist();
        List<TbsDetail> listTbsDetail =  dao.tbsdetails();
        List<Archivelog> listArchivelog = dao.archivelog();
        List<RmanTime> listRmanTime = dao.rmantimedetails();
        List<RmanCurrent> listRmanCurrent = dao.rmancurrentdetails();
        List<RmanHistory> listRmanHistory = dao.rmanhistorydetails();
        List<LongRunningObj> listLongRunningObj = dao.longrunningobjdetails();
        List<CurrentTime> listCurrentTime = dao.curtime();
        model.addAttribute("listBasic", listBasic);
        model.addAttribute("listDefaultTbs", listDefaultTbs);
        model.addAttribute("listTbsDetail", listTbsDetail);
        model.addAttribute("listArchivelog", listArchivelog);
        model.addAttribute("listRmanTime", listRmanTime);
        model.addAttribute("listRmanCurrent", listRmanCurrent);
        model.addAttribute("listRmanHistory", listRmanHistory);
        model.addAttribute("listLongRunningObj",listLongRunningObj);
        model.addAttribute("listCurrentTime",listCurrentTime);



        return "basic";
    }








}
