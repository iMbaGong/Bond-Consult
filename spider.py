from selenium import webdriver

def main():

    #因为我太菜了不懂怎么爬那个表只能先用selenium了
    br=webdriver.Chrome()
    url="http://bond.sse.com.cn/disclosure/info/tb/"
    br.get(url)
    data=[]
    end=False
    while True:
        try:
            nextPage=br.find_element_by_css_selector("#undefined_next")
        except:
            end=True

        tr=br.find_elements_by_tag_name("tr")
        head=tr[0].text.split(" ")
        for i in range(len(tr)-1):
            meta={}
            line=tr[i].text.split(" ")
            for j in range(len(line)-1):
                meta[head[j]]=line[j]
            data.append(meta)
        if end:
            br.close()
            break    
        nextPage.click()
    
    print(data)

if __name__ == "__main__":
    main()