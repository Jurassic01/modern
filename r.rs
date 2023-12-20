use std::io;

struct Task {
    id: i32,
    description: String,
}

struct TaskManager {
    tasks: Vec<Task>,
}

impl TaskManager {
    fn add_task(&mut self) {
        let mut new_task = Task {
            id: 0,
            description: String::new(),
        };

        println!("Enter ID:");
        io::stdin().read_line(&mut new_task.id.to_string())
            .expect("Failed to read ID");
        
        println!("Enter Description:");
        io::stdin().read_line(&mut new_task.description)
            .expect("Failed to read Description");

        self.tasks.push(new_task);
        println!("Task added!");
    }

    fn show_tasks(&self) {
        println!("All Tasks:");
        for task in &self.tasks {
            println!("ID: {} | Description: {}", task.id, task.description);
        }
    }

    fn delete_task(&mut self, id: i32) {
        if let Some(position) = self.tasks.iter().position(|task| task.id == id) {
            self.tasks.remove(position);
            println!("Task deleted!");
        } else {
            println!("Task not found!");
        }
    }
}

fn main() {
    let mut task_manager = TaskManager { tasks: Vec::new() };

    loop {
        println!("\nChoose operation:");
        println!("1. Add Task");
        println!("2. Show Tasks");
        println!("3. Delete Task");
        println!("4. Exit");
        println!("Enter your choice:");

        let mut choice = String::new();
        io::stdin().read_line(&mut choice).expect("Failed to read choice");
        let choice: i32 = match choice.trim().parse() {
            Ok(num) => num,
            Err(_) => {
                println!("Invalid choice! Please enter a number.");
                continue;
            }
        };

        match choice {
            1 => task_manager.add_task(),
            2 => task_manager.show_tasks(),
            3 => {
                println!("Enter Task ID to delete:");
                let mut id = String::new();
                io::stdin().read_line(&mut id).expect("Failed to read ID");
                let id: i32 = match id.trim().parse() {
                    Ok(num) => num,
                    Err(_) => {
                        println!("Invalid ID! Please enter a number.");
                        continue;
                    }
                };
                task_manager.delete_task(id);
            }
            4 => {
                println!("Exiting program...");
                break;
            }
            _ => println!("Invalid choice! Try again."),
        }
    }
}
