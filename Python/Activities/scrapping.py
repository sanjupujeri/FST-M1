soup = BeautifulSoup(content, "html.parser")

table = soup.find("table")

for tr in table.find_all("tr"):
    cells = tr.find_all("td")
    row = [i.text for i in cells]
    print(row)
