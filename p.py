import pandas as pd
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
import seaborn as sns

# Завантаження даних
url = "https://raw.githubusercontent.com/datasciencedojo/datasets/master/titanic.csv"
titanic = pd.read_csv(url)

# Вибір колонок "Sex", "Age" та "Survived"
data = titanic[["Sex", "Age", "Survived"]]

# Перетворення категоріальної змінної "Sex" в числову (dummy-кодування)
data['Sex'] = pd.get_dummies(data['Sex'], drop_first=True)

# Видалення рядків з пропущеними значеннями
data = data.dropna()

# Розділення на вхідні змінні (X) та цільову змінну (y)
X = data[["Sex", "Age"]]
y = data["Survived"]

# Ініціалізація та навчання моделі множинної лінійної регресії
model = LinearRegression()
model.fit(X, y)

# Виведення коефіцієнтів та вільного члена моделі
print("Intercept (вільний член):", model.intercept_)
print("Coefficients (коефіцієнти):", model.coef_)
r_squared = model.score(X, y)
print("R-squared:", r_squared)
# Відображення точок на графіку за допомогою seaborn
sns.scatterplot(x='Age', y='Sex', hue='Survived', data=data, palette='Set1', alpha=0.8)

# Побудова границі рішення моделі
ax = plt.gca()
xlim = ax.get_xlim()
ylim = ax.get_ylim()

xx, yy = pd.np.meshgrid(pd.np.linspace(xlim[0], xlim[1], 100), pd.np.linspace(ylim[0], ylim[1], 100))
Z = model.predict(pd.np.c_[xx.ravel(), yy.ravel()]).reshape(xx.shape)

plt.contourf(xx, yy, Z, alpha=0.3, cmap='Set1')
plt.xlabel('Age')
plt.ylabel('Sex')
plt.title('Linear Regression Decision Boundary')
plt.legend()
plt.show()
