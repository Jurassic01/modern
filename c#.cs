using System;
using System.Collections.Generic;
using System.Linq;

class TaskManager
{
    private class Task
    {
        public int Id { get; set; }
        public string Description { get; set; }
    }

    private List<Task> tasks = new List<Task>();

    public void AddTask()
    {
        Task newTask = new Task();
        Console.Write("Enter ID: ");
        newTask.Id = int.Parse(Console.ReadLine());

        Console.Write("Enter Description: ");
        newTask.Description = Console.ReadLine();

        tasks.Add(newTask);
        Console.WriteLine("Task added!");
    }

    public void ShowTasks()
    {
        Console.WriteLine("All Tasks:");
        foreach (var task in tasks)
        {
            Console.WriteLine($"ID: {task.Id} | Description: {task.Description}");
        }
    }

    public void UpdateTask(int taskId)
    {
        Task taskToUpdate = tasks.FirstOrDefault(t => t.Id == taskId);
        if (taskToUpdate != null)
        {
            Console.Write("Enter new Description: ");
            taskToUpdate.Description = Console.ReadLine();
            Console.WriteLine("Task updated!");
        }
        else
        {
            Console.WriteLine("Task not found!");
        }
    }

    public void DeleteTask(int taskId)
    {
        Task taskToDelete = tasks.FirstOrDefault(t => t.Id == taskId);
        if (taskToDelete != null)
        {
            tasks.Remove(taskToDelete);
            Console.WriteLine("Task deleted!");
        }
        else
        {
            Console.WriteLine("Task not found!");
        }
    }
}

class Program
{
    static void Main()
    {
        TaskManager taskManager = new TaskManager();

        int choice;
        do
        {
            Console.WriteLine("\nChoose operation:");
            Console.WriteLine("1. Add Task");
            Console.WriteLine("2. Show Tasks");
            Console.WriteLine("3. Update Task");
            Console.WriteLine("4. Delete Task");
            Console.WriteLine("5. Exit");
            Console.Write("Enter your choice: ");
            int.TryParse(Console.ReadLine(), out choice);

            switch (choice)
            {
                case 1:
                    taskManager.AddTask();
                    break;
                case 2:
                    taskManager.ShowTasks();
                    break;
                case 3:
                    Console.Write("Enter Task ID to update: ");
                    int taskIdToUpdate = int.Parse(Console.ReadLine());
                    taskManager.UpdateTask(taskIdToUpdate);
                    break;
                case 4:
                    Console.Write("Enter Task ID to delete: ");
                    int taskIdToDelete = int.Parse(Console.ReadLine());
                    taskManager.DeleteTask(taskIdToDelete);
                    break;
                case 5:
                    Console.WriteLine("Exiting program...");
                    break;
                default:
                    Console.WriteLine("Invalid choice! Try again.");
                    break;
            }
        } while (choice != 5);
    }
}
