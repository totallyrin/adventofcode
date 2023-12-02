from ...input import read_lines

import re


def part1(inputs: list[str]) -> int:
    pattern = re.compile(r"\d")
    return sum(
        [int(f"{pattern.findall(i)[0]}{pattern.findall(i)[-1]}") for i in inputs]
    )


def part2(inputs: list[str]) -> int:
    repl = {
        "one": "1",
        "two": "2",
        "three": "3",
        "four": "4",
        "five": "5",
        "six": "6",
        "seven": "7",
        "eight": "8",
        "nine": "9",
    }

    replace_pattern = re.compile(r"(?=(one|two|three|four|five|six|seven|eight|nine))")

    replaced_inputs = [
        replace_pattern.sub(lambda x: repl.get(x.group(1), ""), i) for i in inputs
    ]

    return part1(replaced_inputs)


def read_numbers(line: str):
    ints = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    nums = []
    word = ""
    for char in line:
        if str.isdigit(char):
            nums.append(char)
            pass
        word += char
        if not any(word in num for num in ints):
            word = word[1:]
        elif word in ints:
            nums.append(str(ints.index(word) + 1))
            word = char
    res = nums[0] + nums[-1]
    return int(res)


# lines = read_lines("2023/01/part_2/sample.txt")
lines = read_lines("2023/01/part_2/input.txt")
total = 0
for ln in lines:
    total += read_numbers(ln)
print(total)

print(part2(lines))
