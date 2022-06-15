package com.happyhouse.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.happyhouse.model.NewsDto;

@Service
public class NewsServiceImpl implements NewsService {

	@Override
	public List<NewsDto> newsList() {

		List<NewsDto> list = new ArrayList<NewsDto>();
		String origin_url = "https://land.naver.com";
		String url = "https://land.naver.com/news/headline.naver";
		try {
			Document doc = Jsoup.connect(url).get();
//			System.out.println(doc);
			Elements contents1 = doc.select("ul.headline_list > li > dl > dt:not(.photo)");
			Elements contents2 = doc.select("ul.headline_list > li > dl > dt:not(.photo) > a");
			Elements contents3 = doc.select("ul.headline_list > li > dl > dd");
//			System.out.println(contents3.get(0).child(0).text);
//			System.out.println(contents2.get(0).attr("href"));
//			Elements contents1 = doc.select("div.spot > div.section > div.group2 > ul.list > li > span[id^=h2news]");
//			Elements contents2= doc.select("div.spot > div.section > div.group2 > ul.list > li > span > a");
			for (int i = 0; i < contents1.size(); i++) {
				list.add(new NewsDto(contents1.get(i).text(), origin_url + contents2.get(i).attr("href"),
						contents3.get(i).childNode(0).toString(), contents3.get(i).child(0).text(),
						contents3.get(i).child(1).text()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}
}
