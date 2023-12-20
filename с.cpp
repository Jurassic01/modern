#include <iostream>
#include <vector>
#include <algorithm>

class TaskManager {
private:
    struct Task {
        int id;
        std::string description;
    };

    std::vector<Task> tasks;

public:
    void addTask() {
        Task newTask;
        std::cout << "Enter ID: ";
        std::cin >> newTask.id;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        std::cout << "Enter Description: ";
        std::getline(std::cin, newTask.description);

        tasks.push_back(newTask);
        std::cout << "Task added!\n";
    }

    void showTasks() const {
        std::cout << "All Tasks:\n";
        for (const auto& task : tasks) {
            std::cout << "ID: " << task.id << " | Description: " << task.description << '\n';
        }
    }

    void updateTask(int taskId) {
        auto it = std::find_if(tasks.begin(), tasks.end(), [taskId](const Task& task) {
            return task.id == taskId;
        });

        if (it != tasks.end()) {
            std::cout << "Enter new Description: ";
            std::getline(std::cin, it->description);
            std::cout << "Task updated!\n";
        } else {
            std::cout << "Task not found!\n";
        }
    }

    void deleteTask(int taskId) {
        auto it = std::find_if(tasks.begin(), tasks.end(), [taskId](const Task& task) {
            return task.id == taskId;
        });

        if (it != tasks.end()) {
            tasks.erase(it);
            std::cout << "Task deleted!\n";
        } else {
            std::cout << "Task not found!\n";
        }
    }
};

int main() {
    TaskManager taskManager;

    int choice;
    do {
        std::cout << "\nChoose operation:\n";
        std::cout << "1. Add Task\n";
        std::cout << "2. Show Tasks\n";
        std::cout << "3. Update Task\n";
        std::cout << "4. Delete Task\n";
        std::cout << "5. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1:
                taskManager.addTask();
                break;
            case 2:
                taskManager.showTasks();
                break;
            case 3: {
                int taskId;
                std::cout << "Enter Task ID to update: ";
                std::cin >> taskId;
                taskManager.updateTask(taskId);
                break;
            }
            case 4: {
                int taskId;
                std::cout << "Enter Task ID to delete: ";
                std::cin >> taskId;
                taskManager.deleteTask(taskId);
                break;
            }
            case 5:
                std::cout << "Exiting program...\n";
                break;
            default:
                std::cout << "Invalid choice! Try again.\n";
                break;
        }
    } while (choice != 5);

    return 0;
}
