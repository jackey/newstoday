package main

import (
	"beego"
)

type MainController struct {
	beego.Controller
}

func (this *MainController) Get() {
	this.Ctx.WriteString("hello world")
}

func main() {
	beego.RegisterController("/", &MainController{})
	//beego.HttpPort = 8080 // default
	beego.Run()
}
