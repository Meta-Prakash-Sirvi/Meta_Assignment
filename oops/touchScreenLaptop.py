from abc import ABC, abstractmethod

class TouchScreenLaptop(ABC):
    @abstractmethod
    def scroll(self):
        pass

    @abstractmethod
    def click(self):
        pass


class Hp(TouchScreenLaptop):
    def scroll(self):
        print("Scrolling in HP laptop ")

class Dell(TouchScreenLaptop):
    def scroll(self):
        print("Scrolling in Dell laptop ")

class HpNotebook(Hp):
    def click(self):
        print("clicking in Hp notebook")

class DellNotebook(Dell):
    def click(self):
        print("clicking in dell notebook")


hp = HpNotebook()
hp.scroll()
hp.click()

dell = DellNotebook()
dell.scroll()
dell.click()