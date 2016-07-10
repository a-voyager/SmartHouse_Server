# 智能家居系统(服务端)

# 系统架构思路
1. 阿里云作为服务端，启动后启动两个套接字，一直处于监听状态
2. 树莓派作为中间件，启动后，初始化（包含读取配置文件、初始化硬件等）后，启动套接字连接服务端
3. 树莓派和服务器保持TCP长连接
树莓派：
 * 轮询硬件，获取信息
 * 向服务器轮发信息
 * 收到服务端TCP请求控制硬件
阿里云：
 * 收到数据存入数据库
 * 接收来自终端设备的HTTP请求和TCP请求
 * 向树莓派发送TCP请求控制硬件

# 服务器端接口

查询最后一条信息
URL：http://localhost:8080/last-info.action
参数：无
返回：最后一条信息
```
{"mIsError":false,"mErrorType":0,"mErrorMessage":"","mInfoItems":[{"mId":7501,"mNodeId":0,"mTemperature":"11","mHumidity":"16","mWindSpeed":"","mWindDirection":"","mCurtainState":"","mIsSafe":"","mSmoke":"","mUltrasonicWave":"","mTimeStamp":1467800797000}]}
```

查询符合的最近信息
URL：http://localhost:8080/recent-info.action
参数：count = 数量； period = 间隔
返回：最近所有信息
```
{"mIsError":false,"mErrorType":0,"mErrorMessage":"","mInfoItems":[{"mId":7501,"mNodeId":0,"mTemperature":"11","mHumidity":"16","mWindSpeed":"","mWindDirection":"","mCurtainState":"","mIsSafe":"","mSmoke":"","mUltrasonicWave":"","mTimeStamp":1467800797000},{"mId":7401,"mNodeId":0,"mTemperature":"16","mHumidity":"45","mWindSpeed":"","mWindDirection":"","mCurtainState":"","mIsSafe":"","mSmoke":"","mUltrasonicWave":"","mTimeStamp":1467800796000}]}
```

发送控制节点指令
URL：http://localhost:8080/send-control.action
参数：json = MessageEntity
返回：报头response = "ok" / "error"
```
{"id":0,"text":"openLed"}
```

查询最大值和最小值
URL：http://localhost:8080/most-value.action
参数：type = 类型 temperature humidity
返回：Json 最大值 最小值
```
{"max":29,"min":0}
```

# License
    The MIT License (MIT)

    Copyright (c) 2015 WuHaojie(吴豪杰)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
