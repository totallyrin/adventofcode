from ...input import read_lines


def get_digits(line: str):
    res = ""
    first_found = False
    last_found = False
    for i in range(len(line)):
        j = len(line) - 1 - i
        if not first_found and str.isdigit(line[i]):
            res = line[i] + res
            first_found = True
        if not last_found and str.isdigit(line[j]):
            res += line[j]
            last_found = True
    return int(res)


# lines = read_lines("2023/01/part_1/sample.txt")
lines = read_lines("2023/01/part_1/input.txt")
total = 0
for ln in lines:
    total += get_digits(ln)
print(total)
