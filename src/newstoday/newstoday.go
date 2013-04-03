package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"os/signal"
)

type Page struct {
	title string
	body  []byte
}

func (p *Page) open(title string) *Page {
	filename := title + ".html"
	page := &Page{}
	page.body, _ = ioutil.ReadFile(filename)
	page.title = title
	return page
}

func (p *Page) save(page Page) error {
	filename := page.title + ".html"
	return ioutil.WriteFile(filename, page.body, 0600)
}

func handler(w http.ResponseWriter, r *http.Request) {
	page := Page{}
	opened_page := page.open("index")
	fmt.Fprintf(w, "%s", opened_page.body)
	//fmt.Fprintf(w, "Hi I like %s and %s", r.URL.Path[1:], r.URL.RawQuery[0:])
}

func main() {
	// Add signal handler
	c := make(chan os.Signal, 1)
	signal.Notify(c, os.Interrupt)
	go func() {
		for sig := range c {
			fmt.Println("%i", sig)
			os.Exit(1)
		}
	}()
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}
