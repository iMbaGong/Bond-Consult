from selenium import webdriver
import time
import json

def main():
    # 因为我太菜了不懂怎么爬那个表只能先用selenium了
    br = webdriver.Chrome()
    url = "http://bond.sse.com.cn/disclosure/info/tb/#"
    br.get(url)
    data = []
    end = False
    pages = 0
    while True:
        try:
            nextPage = None
            nextPage = br.find_element_by_class_name("paging_next")
        except:
            end = True
        pages += 1
        tr = br.find_elements_by_tag_name("tr")
        head = tr[0].text.split(" ")
        for i in range(1, len(tr)):
            meta = {}
            line = tr[i].text.split(" ")
            for j in range(len(line)):
                meta[head[j]] = line[j]
            data.append(meta)
            #print(meta)
        if end:
            br.close()
            break
        nextPage.click()
        time.sleep(0.2)
    with open('data.json', 'w', encoding='utf-8') as f:
        json.dump(data,f)
    #print(pages)


if __name__ == "__main__":
    main()
