
# coding: utf-8

import datetime
import pandas as pd
import urllib.request
from bs4 import BeautifulSoup

url = 'http://www.bna.ao/Servicos/pesquisa_cambios.aspx?idc=825&idl=2'

headers = {
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7",
    "Content-Type": "application/x-www-form-urlencoded",
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36"
}

response = urllib.request.Request(url, headers = headers)
soup = BeautifulSoup(urllib.request.urlopen(response), 'lxml')
ViewState = soup.find('input', attrs={'id': '__VIEWSTATE'})['value']
EventValidation = soup.find('input', attrs={'id': '__EVENTVALIDATION'})['value']
ViewStateGenerator = soup.find('input', attrs={'id': '__VIEWSTATEGENERATOR'})['value']

while(True):
    i=input('Enter your option: \n'+'1. today\n'+'2. choose another day\n')
    if i=='1':
        print('You choose date: ' + datetime.datetime.now().strftime('%m/%d/%Y'))
        dateFormatted = datetime.datetime.now().strftime('%m/%d/%Y')
        yearOption = datetime.datetime.now().strftime('%m/%d/%Y').split('/')[2]
        monthOption = datetime.datetime.now().strftime('%m/%d/%Y').split('/')[1]
        dayOption = datetime.datetime.now().strftime('%m/%d/%Y').split('/')[0]
        break
    if i=='2':
        selectedDate = input('Enter your date in format YYYYMMDD: ')
        selectedDateFormat = datetime.datetime.strptime(selectedDate, '%Y%m%d')
        dateFormatted = selectedDateFormat.strftime('%m/%d/%Y')
        print('The date you choose is: ' + selectedDate)
        yearOption =  '%04d' % selectedDateFormat.year
        monthOption = '%02d' % selectedDateFormat.month
        dayOption = '%02d' % selectedDateFormat.day
        break
    else:
        print('Please enter value 1 or 2 and try again: ')
        
currencyOption = [i.text for i in soup.find('select', attrs={'id': 'ctl00_ctl01_DropDownMoeda'}).find_all('option')][1:]
currencyOptionValue = [i['value'] for i in soup.find('select', attrs={'id': 'ctl00_ctl01_DropDownMoeda'}).find_all('option')][1:]
currencyDict = dict(zip(currencyOptionValue, currencyOption))

def getExRates(optionValue):
    payload = {
    '__VIEWSTATE': ViewState,
    '__EVENTVALIDATION': EventValidation,
    "__EVENTTARGET":"" ,
    "__EVENTARGUMENT":"" ,
    "__VIEWSTATEGENERATOR": ViewStateGenerator,
    "__VIEWSTATEENCRYPTED":"", 
    "ctl00$ctl01$DropDownDia": dayOption,
    "ctl00$ctl01$DropDownMes": monthOption,
    "ctl00$ctl01$DropDownAno": yearOption,
    "ctl00$ctl01$DropDownMoeda": optionValue,
    "ctl00$ctl01$send": "Search"
    }
    dataparseurl = urllib.parse.urlencode(payload).encode('ascii')
    r = urllib.request.Request(url, data=dataparseurl, headers=headers)
    soup = BeautifulSoup(urllib.request.urlopen(r), 'lxml')
    dataArray = [currencyDict[optionValue]]
    try:
        for i in soup.find('div', attrs={'class':'table-responsive'}).find('tbody').find_all('td'):
            dataArray.append(i.text.strip())
    except:
        for i in range(3):
            dataArray.append('NA')
    return dataArray

currencyFull = []
for i in currencyOptionValue:
    currencyFull.append(getExRates(i))
    print(currencyDict[i] + ' downloaded.')

df = pd.DataFrame(currencyFull)
df.columns = ['Currency', 'Purchase', 'Sale', 'Average']
df['Date'] = [dateFormatted]*len(df)
df = df[['Currency', 'Date', 'Purchase', 'Sale', 'Average']]
df.to_csv('ExRates.csv', index=None)

