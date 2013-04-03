package main

import (
	"fmt"
	"net/http"
)

func handler(w http.ResponseWrite, r *http.Request) {
	fmt.Fprintf(w, "Hi,I am from %s", r.URL.path[1:])
}

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}
