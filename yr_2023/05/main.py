import pprint

from yr_2023.input import read_lines


def sanitize_input(lines):
    sanitized = {}
    key = ""
    for line in lines:
        if "seeds:" in line:
            sanitized[line.split(":")[0]] = (line.split(": ")[1]).split(" ")
        else:
            if "map:" in line:
                key = line.replace(":", "")
                sanitized[key] = []
            else:
                sanitized[key].append(line.split(" "))
    return sanitized


def part_1(lines):
    input = sanitize_input(lines)
    maps = ["seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water",
            "water-to-light", "light-to-temperature",
            "temperature-to-humidity", "humidity-to-location"]
    locations = input["seeds"]
    for item in range(len(locations)):
        for name in maps:
            for mp in input[name + " map"]:
                diff = int(mp[0]) - int(mp[1])
                if int(locations[item]) in range(int(mp[1]), int(mp[1]) + int(mp[2])):
                    locations[item] = str(int(locations[item]) + diff)
                    break
    return min(list(map(int, locations)))


pp = pprint.PrettyPrinter(indent=4)

# l = read_lines("yr_2023/05/sample.txt")
l = read_lines("yr_2023/05/input.txt")

pp.pprint(sanitize_input(l.copy()))
print(part_1(l.copy()))