package com.bankSpring.bankSystemSpring.Response

class Response
{
    var action = "" //eg deposit or transfer or getting account number (high level)
    var process = ArrayList<String>()//eg testing pin Number
    var currentProcess = 0
    var result = ""
}