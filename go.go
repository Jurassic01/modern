package main

import (
	"fmt"
)

type Task struct {
	ID          int
	Description string
}

type TaskManager struct {
	Tasks []Task
}

func (tm *TaskManager) AddTask() {
	var newTask Task

	fmt.Println("Enter ID:")
	fmt.Scan(&newTask.ID)

	fmt.Println("Enter Description:")
	fmt.Scan(&newTask.Description)

	tm.Tasks = append(tm.Tasks, newTask)
	fmt.Println("Task added!")
}

func (tm TaskManager) ShowTasks() {
	fmt.Println("All Tasks:")
	for _, task := range tm.Tasks {
		fmt.Printf("ID: %d | Description: %s\n", task.ID, task.Description)
	}
}

func (tm *TaskManager) DeleteTask(id int) {
	for i, task := range tm.Tasks {
		if task.ID == id {
			tm.Tasks = append(tm.Tasks[:i], tm.Tasks[i+1:]...)
			fmt.Println("Task deleted!")
			return
		}
	}
	fmt.Println("Task not found!")
}

func main() {
	var taskManager TaskManager

	for {
		var choice int
		fmt.Println("\nChoose operation:")
		fmt.Println("1. Add Task")
		fmt.Println("2. Show Tasks")
		fmt.Println("3. Delete Task")
		fmt.Println("4. Exit")
		fmt.Print("Enter your choice: ")
		fmt.Scan(&choice)

		switch choice {
		case 1:
			taskManager.AddTask()
		case 2:
			taskManager.ShowTasks()
		case 3:
			var id int
			fmt.Print("Enter Task ID to delete: ")
			fmt.Scan(&id)
			taskManager.DeleteTask(id)
		case 4:
			fmt.Println("Exiting program...")
			return
		default:
			fmt.Println("Invalid choice! Try again.")
		}
	}
}
