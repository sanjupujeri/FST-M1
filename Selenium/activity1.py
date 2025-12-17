from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


driver = webdriver.Chrome()
wait = WebDriverWait(driver, 10)


driver.get("https://training-support.net")


print("Home Page Title:", driver.title)


about_us = wait.until(
    EC.element_to_be_clickable((By.LINK_TEXT, "About Us"))
)
about_us.click()

print("About Us Page Title:", driver.title)


driver.quit()
