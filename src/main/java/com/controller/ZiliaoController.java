
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 学习资料
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ziliao")
public class ZiliaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZiliaoController.class);

    private static final String TABLE_NAME = "ziliao";

    @Autowired
    private ZiliaoService ziliaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private JiaoshiService jiaoshiService;//教师
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private YonghuService yonghuService;//学生
    @Autowired
    private ZiliaoCollectionService ziliaoCollectionService;//学习资料收藏
    @Autowired
    private ZiliaoCommentbackService ziliaoCommentbackService;//学习资料评价
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教师".equals(role))
            params.put("jiaoshiId",request.getSession().getAttribute("userId"));
        params.put("ziliaoDeleteStart",1);params.put("ziliaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = ziliaoService.queryPage(params);

        //字典表数据转换
        List<ZiliaoView> list =(List<ZiliaoView>)page.getList();
        for(ZiliaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoEntity ziliao = ziliaoService.selectById(id);
        if(ziliao !=null){
            //entity转view
            ZiliaoView view = new ZiliaoView();
            BeanUtils.copyProperties( ziliao , view );//把实体数据重构到view中
            //级联表 教师
            //级联表
            JiaoshiEntity jiaoshi = jiaoshiService.selectById(ziliao.getJiaoshiId());
            if(jiaoshi != null){
            BeanUtils.copyProperties( jiaoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "jiaoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJiaoshiId(jiaoshi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("教师".equals(role))
            ziliao.setJiaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZiliaoEntity> queryWrapper = new EntityWrapper<ZiliaoEntity>()
            .eq("ziliao_name", ziliao.getZiliaoName())
            .eq("jiaoshi_id", ziliao.getJiaoshiId())
            .eq("zan_number", ziliao.getZanNumber())
            .eq("cai_number", ziliao.getCaiNumber())
            .eq("ziliao_types", ziliao.getZiliaoTypes())
            .in("ziliao_yesno_types", new Integer[]{1,2})
            .eq("ziliao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoEntity ziliaoEntity = ziliaoService.selectOne(queryWrapper);
        if(ziliaoEntity==null){
            ziliao.setZiliaoYesnoTypes(1);
            ziliao.setZiliaoDelete(1);
            ziliao.setInsertTime(new Date());
            ziliao.setCreateTime(new Date());
            ziliaoService.insert(ziliao);
            return R.ok();
        }else {
            if(ziliaoEntity.getZiliaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(ziliaoEntity.getZiliaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());
        ZiliaoEntity oldZiliaoEntity = ziliaoService.selectById(ziliao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("教师".equals(role))
//            ziliao.setJiaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(ziliao.getZiliaoPhoto()) || "null".equals(ziliao.getZiliaoPhoto())){
                ziliao.setZiliaoPhoto(null);
        }
        if("".equals(ziliao.getZiliaoFile()) || "null".equals(ziliao.getZiliaoFile())){
                ziliao.setZiliaoFile(null);
        }

            ziliaoService.updateById(ziliao);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZiliaoEntity ziliaoEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,ziliaoEntity:{}",this.getClass().getName(),ziliaoEntity.toString());

        ZiliaoEntity oldZiliao = ziliaoService.selectById(ziliaoEntity.getId());//查询原先数据

//        if(ziliaoEntity.getZiliaoYesnoTypes() == 2){//通过
//            ziliaoEntity.setZiliaoTypes();
//        }else if(ziliaoEntity.getZiliaoYesnoTypes() == 3){//拒绝
//            ziliaoEntity.setZiliaoTypes();
//        }
        ziliaoService.updateById(ziliaoEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZiliaoEntity> oldZiliaoList =ziliaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZiliaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
            ziliaoEntity.setId(id);
            ziliaoEntity.setZiliaoDelete(2);
            list.add(ziliaoEntity);
        }
        if(list != null && list.size() >0){
            ziliaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<ZiliaoEntity> ziliaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZiliaoEntity ziliaoEntity = new ZiliaoEntity();
//                            ziliaoEntity.setZiliaoName(data.get(0));                    //学习资料标题 要改的
//                            ziliaoEntity.setZiliaoPhoto("");//详情和图片
//                            ziliaoEntity.setZiliaoFile(data.get(0));                    //学习资料文件 要改的
//                            ziliaoEntity.setJiaoshiId(Integer.valueOf(data.get(0)));   //教师 要改的
//                            ziliaoEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            ziliaoEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            ziliaoEntity.setZiliaoTypes(Integer.valueOf(data.get(0)));   //学习资料类型 要改的
//                            ziliaoEntity.setZiliaoContent("");//详情和图片
//                            ziliaoEntity.setZiliaoYesnoTypes(Integer.valueOf(data.get(0)));   //学习资料审核 要改的
//                            ziliaoEntity.setZiliaoYesnoText(data.get(0));                    //审核回复 要改的
//                            ziliaoEntity.setZiliaoDelete(1);//逻辑删除字段
//                            ziliaoEntity.setInsertTime(date);//时间
//                            ziliaoEntity.setCreateTime(date);//时间
                            ziliaoList.add(ziliaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        ziliaoService.insertBatch(ziliaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZiliaoView> returnZiliaoViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("ziliaoYesnoTypes",2);
        PageUtils pageUtils = ziliaoCollectionService.queryPage(params1);
        List<ZiliaoCollectionView> collectionViewsList =(List<ZiliaoCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZiliaoCollectionView collectionView:collectionViewsList){
            Integer ziliaoTypes = collectionView.getZiliaoTypes();
            if(typeMap.containsKey(ziliaoTypes)){
                typeMap.put(ziliaoTypes,typeMap.get(ziliaoTypes)+1);
            }else{
                typeMap.put(ziliaoTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("ziliaoTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("ziliaoYesnoTypes",2);
            PageUtils pageUtils1 = ziliaoService.queryPage(params2);
            List<ZiliaoView> ziliaoViewList =(List<ZiliaoView>)pageUtils1.getList();
            returnZiliaoViewList.addAll(ziliaoViewList);
            if(returnZiliaoViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("ziliaoYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = ziliaoService.queryPage(params);
        if(returnZiliaoViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZiliaoViewList.size();//要添加的数量
            List<ZiliaoView> ziliaoViewList =(List<ZiliaoView>)page.getList();
            for(ZiliaoView ziliaoView:ziliaoViewList){
                Boolean addFlag = true;
                for(ZiliaoView returnZiliaoView:returnZiliaoViewList){
                    if(returnZiliaoView.getId().intValue() ==ziliaoView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZiliaoViewList.add(ziliaoView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZiliaoViewList = returnZiliaoViewList.subList(0, limit);
        }

        for(ZiliaoView c:returnZiliaoViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZiliaoViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = ziliaoService.queryPage(params);

        //字典表数据转换
        List<ZiliaoView> list =(List<ZiliaoView>)page.getList();
        for(ZiliaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiliaoEntity ziliao = ziliaoService.selectById(id);
            if(ziliao !=null){


                //entity转view
                ZiliaoView view = new ZiliaoView();
                BeanUtils.copyProperties( ziliao , view );//把实体数据重构到view中

                //级联表
                    JiaoshiEntity jiaoshi = jiaoshiService.selectById(ziliao.getJiaoshiId());
                if(jiaoshi != null){
                    BeanUtils.copyProperties( jiaoshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiaoshiId(jiaoshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZiliaoEntity ziliao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,ziliao:{}",this.getClass().getName(),ziliao.toString());
        Wrapper<ZiliaoEntity> queryWrapper = new EntityWrapper<ZiliaoEntity>()
            .eq("ziliao_name", ziliao.getZiliaoName())
            .eq("jiaoshi_id", ziliao.getJiaoshiId())
            .eq("zan_number", ziliao.getZanNumber())
            .eq("cai_number", ziliao.getCaiNumber())
            .eq("ziliao_types", ziliao.getZiliaoTypes())
            .in("ziliao_yesno_types", new Integer[]{1,2})
            .eq("ziliao_yesno_text", ziliao.getZiliaoYesnoText())
            .eq("ziliao_delete", ziliao.getZiliaoDelete())
//            .notIn("ziliao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiliaoEntity ziliaoEntity = ziliaoService.selectOne(queryWrapper);
        if(ziliaoEntity==null){
                ziliao.setZanNumber(1);
                ziliao.setCaiNumber(1);
            ziliao.setZiliaoYesnoTypes(1);
            ziliao.setZiliaoDelete(1);
            ziliao.setInsertTime(new Date());
            ziliao.setCreateTime(new Date());
        ziliaoService.insert(ziliao);

            return R.ok();
        }else {
            if(ziliaoEntity.getZiliaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(ziliaoEntity.getZiliaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

