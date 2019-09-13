from selenium import webdriver
import time
import json

def main():
    # 因为我太菜了不懂怎么爬那个表只能先用selenium了
    br = webdriver.Chrome()
    url = "http://finance.sina.com.cn/roll/index.d.html"
    br.get(url)
    data = []
    end = False
    pages = 0
    nextPage = None
    nextPage = br.find_element_by_link_text("下一页")
    while True:
        try:
            nextPage = None
            nextPage = br.find_element_by_link_text("下一页")
        except:
            end = True
        pages += 1
        tr = br.find_elements_by_class_name("list_009")
        ul_num=1
        while True:
            ul=br.find_elements_by_xpath('//*[@id="Main"]/div[3]/ul['+str(ul_num)+']')
            if ul==[]:
                break
            li_num=1
            while True:
                news=br.find_elements_by_xpath('//*[@id="Main"]/div[3]/ul['+str(ul_num)+']/li['+str(li_num)+']/a')
                if news==[]:
                    break
                title=news[0].text
                link=news[0].get_attribute('href')
                news_time=br.find_elements_by_xpath('//*[@id="Main"]/div[3]/ul['+str(ul_num)+']/li['+str(li_num)+']/span')
                news_time=news_time[0].text
                meta = {}
                meta["title"]=title
                meta["time"]=news_time
                meta["link"]=link
                data.append(meta)
                li_num+=1
            ul_num+=1


        if end:
            print()
            br.close()
            break
        nextPage.click()
        time.sleep(0.1)
    with open('news.json', 'w') as f:
        json.dump(data,f)
    print(pages)


if __name__ == "__main__":
    main()