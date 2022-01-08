package com.example.demo.app;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller                 // コントローラであることを明示
@RequestMapping("/sample")  // 下記メソッドが処理するURLを定義   http://ホスト名/sample
public class SampleController {
	
	private final JdbcTemplate jdbcTemplate;  // クラスの定義と初期化
	
	@Autowired  // インスタンスを生成し別クラスに代入する。　　　Dependency Injection...デザインパターンの一つ。クラスの依存性を薄くし、再利用可能にする。　https://nuxtblog.work/blog/fac9n5nqdxf/　　https://qiita.com/haseesah/items/32ad604c15328e91fb75  https://learning-collection.com/springboot%E5%85%A5%E9%96%80-vol-8%EF%BC%9Adi%E3%82%92%E7%90%86%E8%A7%A3%E3%81%97%E3%82%88%E3%81%86/
	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@GetMapping("/test")   // Spring Framework 4.3から @PostMapping("/sample") などCRUDのリクエストを記述可能   https://www.shookuro.com/entry/2016/08/21/134349
	public String test(Model model) {
		
		String sql = "SELECT id, name, email FROM inquiry WHERE id = 1";   // sqlの設定
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);           // Mapとしてレコードを取得する　　https://zenn.dev/waka_morita/articles/ed441b3e5b0b76
		System.out.println(map);
		model.addAttribute("title", "Inquiry Form");  //  インスタンスのtitleフィールドにInquiry Formを設定
		model.addAttribute("name", map.get("name"));
		model.addAttribute("email", map.get("email"));
		System.out.println(model);
		return "test";
	}
}
