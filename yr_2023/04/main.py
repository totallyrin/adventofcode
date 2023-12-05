import pprint

from yr_2023.input import read_lines


def format_cards(lines):
    # format cards
    for i in range(len(lines)):
        # remove "Card x: " from string, since game # is index + 1
        temp = lines[i].split(":")
        lines[i] = temp[1].strip()
        # split winning #s from existing #s
        lines[i] = lines[i].split(" | ")
        temp = []
        for set in lines[i]:
            temp.append(set.split(" "))
        lines[i] = temp
    return lines

def format_cards_v2(lines):
    # format cards
    cards = {}
    for i in range(len(lines)):
        # remove "Card x: " from string, since game # is index + 1
        temp = lines[i].split(":")
        lines[i] = temp[1].strip()
        # split winning #s from existing #s
        lines[i] = lines[i].split(" | ")
        temp = []
        for set in lines[i]:
            temp.append(set.split(" "))
        cards[i + 1] = [temp, 1]
    return cards


def get_values(lines):
    lines = format_cards(lines)
    total = 0
    for card in lines:
        points = 0
        for num in card[1]:
            if num in card[0] and num != "":
                if points < 1:
                    points = 1
                else:
                    points *= 2
        total += points
    return total


def get_cards(lines):
    for card in lines:
        points = 0
        for num in lines[card][0][1]:
            if num in lines[card][0][0] and num != "":
                points += 1
        if points > 0:
            for i in range(points):
                lines[card + i + 1][1] += 1 * lines[card][1]
    # calculate total # of cards
    total = 0
    for card in lines:
        total += lines[card][1]
    return total



pp = pprint.PrettyPrinter(indent=4)

# l = read_lines("yr_2023/04/sample.txt")
l = read_lines("yr_2023/04/input.txt")

# print(format_cards(l.copy()))
print(get_values(l.copy()))

print()

l_copy = format_cards_v2(l.copy())
print(get_cards(l_copy))