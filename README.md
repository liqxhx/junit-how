## [junit下载](https://sourceforge.net/projects/junit/files/junit/)

## 几个概念
### 测试运行器：执行测试集的程序
测试运行器(Test Runner)是运行测试集的程序，使用@RunWith指定运行器，如果没有指定任何运行器，JUnit会使用一个默认的运行器
```

org.junit.internal.runners.JUnit38ClassRunner	为了向后兼容Junit3.8的运行器
org.junit.runners.JUnit4	Junit4的测试运行器
org.junit.runners.Parameterized	可以使用不同参数来运行相同测试集的运行器
org.junit.runners.Suite	包含不同测试的容器

```
### 测试集
* 一组测试，测试集是把多个相关测试归入一组的便捷方式
* 测试集是把多个相关测试归入一个组的表达方式，如果我们没有明确的定义一个测试集，那么Juint会自动的提供一个测试集，一个测试集一般将同一个包的测试类归入一组；
```
测试集的类是一个空类，不包含任何方法，更改测试运行器为@RunWith(Suite.class) 
使用@SuiteClasses定义测试集，传入需要测试的测试类或测试集，是一个集合。
```
### 测试方法
### 测试类
