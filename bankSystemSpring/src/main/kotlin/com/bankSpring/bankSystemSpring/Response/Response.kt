package com.bankSpring.bankSystemSpring.Response

class Response
{
    var action = "" //eg deposit or transfer or getting account number (high level)
    private val actionHeader = "Action: "
    var process = ArrayList<String>()//eg testing pin Number
    private val processHeader = "Process: "
    var currentProcess = 0
    private val currentProcessHeader = "Current: "
    var result = ""
    private val resultHeader = "Result: "

    fun newResponse():Response
    {
        return Response()
    }
    fun addAction(model: Response, action: String ):Response
    {
        model.action = action
        model.currentProcess = 0
        model.process.clear()
        return model
    }

    fun addProcess(model: Response, process: String): Response
    {
        model.process.add(process)
        currentProcess++
        return model
    }

    fun addResult(model: Response, result: String): Response
    {
        model.result = result
        return model
    }


    private fun actionPrint(model: Response): String
    {
        return model.actionHeader + model.action
    }

    private fun processPrint(model:Response):String
    {
        var processes = ""

        for(p in model.process)
        {
             processes += ln(model.processHeader + p)
        }
        return processes
    }

    private fun currentProcessPrint(model:Response): String
    {
        return model.currentProcessHeader + model.currentProcess
    }

    private fun resultPrint(model:Response):String
    {
        return model.resultHeader + model.result
    }

    private fun ln(txt: String): String
    {
        return txt + "\n"
    }

    private fun tab(txt: String): String
    {
        return txt + "\t"
    }


    fun print(model: Response) : String
    {
        return ln(actionPrint(model)) + tab(currentProcessPrint(model)) +  ln(processPrint(model)) + ln(resultPrint(model))
    }
}