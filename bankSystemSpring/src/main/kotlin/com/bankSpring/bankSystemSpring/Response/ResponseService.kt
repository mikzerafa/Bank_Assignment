package com.bankSpring.bankSystemSpring.Response

class ResponseService
{
    fun newResponse(): Response
    {
        return Response()
    }
    fun addAction(model: Response, action: String ): Response
    {
        model.action = action
        model.currentProcess = 0
        model.process.clear()
        return model
    }

    fun addProcess(model: Response, process: String): Response
    {
        model.process.add(process)
        model.currentProcess++
        return model
    }

    fun addResult(model: Response, result: String): Response
    {
        model.result = result
        return model
    }


    private fun actionPrint(model: Response): String
    {
        return model.action
    }

    private fun processPrint(model: Response):String
    {
        var processes = ""

        for(p in model.process)
        {
            processes += ln(p)
        }
        return processes
    }

    private fun currentProcessPrint(model: Response): String
    {
        return model.currentProcess.toString()
    }

    private fun resultPrint(model: Response):String
    {
        return model.result
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

    fun merge(mainAction: Response, withAction: Response): Response
    {
        //with action results append main action and results are taken as a process

        for(p in withAction.process)
        {
            mainAction.process.add(p)
        }

        mainAction.process.add(withAction.result)

        return mainAction
    }
}