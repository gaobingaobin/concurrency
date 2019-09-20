package com.example.concurrency.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author: Born
 * @create: 2018/6/13
 **/
public class CodeGenerator {

    /**
     *  所有配置源码里面都有注释,不懂可以点进去看
     * @param args
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUsername("root");
        dsc.setPassword("angsi2019");
        dsc.setUrl("jdbc:mysql://10.100.9.102:3306/oms_orders?characterEncoding=utf8&useSSL=true");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        mpg.setDataSource(dsc);

        GlobalConfig gc = new GlobalConfig();
        gc.setDateType(DateType.ONLY_DATE);
        gc.setOutputDir("code");//输出文件
        gc.setSwagger2(true);
        gc.setFileOverride(true);// 文件是否覆盖
        gc.setActiveRecord(false);// 开启 ActiveRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("gaobin");//文件作者
        gc.setIdType(IdType.AUTO); //ID生产策略
        gc.setServiceImplName("%sDao");//为了生成DAO
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("model");//对应model包名
        pc.setParent("com.angsi.factory");//包名前缀
        pc.setModuleName("dao");//dao包名
        pc.setServiceImpl("");//为了生成DAO
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
       //strategy.setTablePrefix(new String[]{"t_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setEntityLombokModel(true); //是否使用lombok
        strategy.entityTableFieldAnnotationEnable(true);// 数据表字段注解是否需要
        strategy.setEntityColumnConstant(false);//表字段常量
        // 否需要
        strategy.setInclude(new String[]{"factory_task"}); // 需要生成的表
        mpg.setStrategy(strategy);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java");//不生成controller
        tc.setServiceImpl("/templates/dao.java");//为了生成DAO
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }
}
