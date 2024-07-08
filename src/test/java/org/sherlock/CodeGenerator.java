package org.sherlock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@SpringBootTest
public class CodeGenerator {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${mybatis-plus.generator.global.out-path}")
    private String outpath;

    @Value("${mybatis-plus.generator.global.author}")
    private String author;

    @Value("${mybatis-plus.generator.package.parent-package-name}")
    private String parentPackageName;

    @Value("${mybatis-plus.generator.package.module-package-name}")
    private String modulePackageName;

    @Test
    public void codeGenerator() {
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(author) // 设置作者名
                        .disableOpenDir() // 允许自动打开输出目录
                        .outputDir(outpath) // 设置输出目录
                        .enableSpringdoc() // springdoc
//                        .enableKotlin() // 开启 Kotlin 模式
//                        .enableSwagger() // 开启 Swagger 模式
                        .dateType(DateType.ONLY_DATE) // 设置时间类型策略
                        .commentDate("yyyy-MM-dd") // 设置注释日期格式
                )
                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent(parentPackageName) // 设置父包名
//                        .moduleName(modulePackageName) // 设置父包模块名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "src\\main\\resources\\mapper\\SysUserMapper.xml"))
                        .pathInfo(Collections.singletonMap(OutputFile.service, "src\\main\\java\\org\\sherlock\\service"))
                        .pathInfo(Collections.singletonMap(OutputFile.serviceImpl, "src\\main\\java\\org\\sherlock\\service\\impl"))
                        .pathInfo(Collections.singletonMap(OutputFile.mapper, "src\\main\\java\\org\\sherlock\\mapper"))
                        .pathInfo(Collections.singletonMap(OutputFile.controller, "src\\main\\java\\org\\sherlock\\controller"))
                        .pathInfo(Collections.singletonMap(OutputFile.entity, "src\\main\\java\\org\\sherlock\\entity")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔 所有输入all")))
                        .enableCapitalMode() // 开启大写命名
                        .enableSkipView() // 开启跳过视图
                        .disableSqlFilter() // 禁用 SQL 过滤
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .enableTableFieldAnnotation()
                        .enableFileOverride() // 覆盖已生成文件
//                        .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                        .idType(IdType.ASSIGN_ID)
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImp")
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        log.info("生成成功");
    }
    // 处理 all 情况
    protected List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
