package main

import (
	"fmt"
)

type Method struct {
	x, y int
}

func (m *Method) plus() int {
	return m.x + m.y
}

func (m *Method) showme() string {
	return "I am Method"
}

func (m *Method) tostring() string {
	return string(m.x)
}

var (
	a = Method{10, 20}
)

var (
	b = []int{1, 2, 3, 4}
)

func main() {
	fmt.Println(b)
}
