package io.renren;

import io.renren.service.SysGeneratorService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("io.renren.dao")
public class RenrenApplication  implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	SysGeneratorService sysGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(RenrenApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//		generateCode(generateAll());
		generateCode(generateAll());
	}

	public List<String> generateAll(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("page",1);
		map.put("limit",Integer.MAX_VALUE);
		PageUtils pageUtils = sysGeneratorService.queryList(new Query(map));
		List<?> list = pageUtils.getList();
		List<String> tableName = list.stream().map(item -> {
			return (String)((Map) item).get("tableName");
		}).collect(Collectors.toList());
		return tableName;
	}

	public void generateCode(List<String> tableName){
		byte[] bytes = sysGeneratorService.generatorCode(tableName.toArray(new String[tableName.size()]));
		FileOutputStream fileOutputStream =null;
		try {

			fileOutputStream =new FileOutputStream("./data.zip");
			IOUtils.write(bytes,fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileOutputStream.close();
				System.out.println("代码生成完毕！！！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
