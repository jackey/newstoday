package main

import (
	"fmt"
	"io/ioutil"
)

type Page struct {
	title string
	body  []byte
}

func (p *Page) save() error {
	filename := p.title + ".txt"
	return ioutil.WriteFile(filename, p.body, 0600)
}

func (p *Page) loadPage(title string) (*Page, error) {
	filename := title + ".txt"
	body, _ := ioutil.ReadFile(filename)

	return &Page{title, body}, nil
}

func main() {
	p := Page{"test_page", []byte("Hello world, Page")}
	p.save()

	p2, _ := p.loadPage("test_page")
	fmt.Println(string(p2.body))
}
