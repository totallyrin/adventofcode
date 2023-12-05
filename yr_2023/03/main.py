import pprint

from yr_2023.input import read_lines


def get_gear_locs(lines):
    gear_locs = []
    for line in range(len(lines)):
        for char in range(len(lines[line])):
            if lines[line][char] == "*":
                gear_locs.append((char, line))
    return gear_locs


def get_symbol_locs(lines):
    symbol_locs = []
    for line in range(len(lines)):
        for char in range(len(lines[line])):
            if not str.isalnum(lines[line][char]) and lines[line][char] != ".":
                symbol_locs.append((char, line))
    return symbol_locs


def read_part_nums(lines):
    symbol_locs = get_symbol_locs(lines)
    part_nums = []
    for line in range(len(lines)):
        num = ""
        is_adj = False
        for char in range(len(lines[line])):
            # check if char is digit
            if str.isdigit(lines[line][char]):
                num += str(lines[line][char])
                # check if adj to symbol
                for symbol in symbol_locs:
                    if abs(symbol[0] - char) <= 1 and abs(symbol[1] - line) <= 1:
                        is_adj = True
            if not str.isalnum(lines[line][char]):
                if is_adj:
                    part_nums.append(int(num))
                is_adj = False
                num = ""
        if is_adj:
            part_nums.append(int(num))
    return part_nums


def get_gear_ratios(lines):
    gear_locs = get_gear_locs(lines)
    gear_ratios = []
    adj = {}
    for line in range(len(lines)):
        num = ""
        is_adj = False
        saved_gear = ()
        for char in range(len(lines[line])):
            # check if char is digit
            if str.isdigit(lines[line][char]):
                num += str(lines[line][char])
                # check if adj to gear
                for gear in gear_locs:
                    if abs(gear[0] - char) <= 1 and abs(
                            gear[1] - line) <= 1:
                        is_adj = True
                        saved_gear = gear
                        break
            if not str.isalnum(lines[line][char]):
                if is_adj:
                    if saved_gear in adj.keys():
                        adj[saved_gear].append(int(num))
                    else:
                        adj[saved_gear] = [int(num)]
                is_adj = False
                num = ""
        if is_adj:
            if saved_gear in adj.keys():
                adj[saved_gear].append(int(num))
            else:
                adj[saved_gear] = [int(num)]
    for key in adj.keys():
        if len(adj[key]) == 2:
            gear_ratios.append(adj[key][0] * adj[key][1])
    return gear_ratios


pp = pprint.PrettyPrinter(indent=4)

# l = read_lines("yr_2023/03/sample.txt")
l = read_lines("yr_2023/03/input.txt")

# print(part_1(l.copy()))
# print(part_2(l.copy()))

# print(get_symbol_locs(l.copy()))
# print(read_part_nums(l.copy()))
print(sum(read_part_nums(l.copy())))

print()

# print(get_gear_locs(l.copy()))
# print(get_gear_ratios(l.copy()))
print(sum(get_gear_ratios(l.copy())))
