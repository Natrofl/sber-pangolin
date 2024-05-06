def letter_repeats_replacement(String s) {
    count = 1
    String new_string = '';
    for (i in 1..<s.length()) {
        if (s[i] == s[i-1]){
            count++;
        } else {
            if (count > 1) {
                new_string += s[i-1] + count
            } else {
                new_string += s[i-1]
            }
            count = 1
        }
    }

    if (count > 1) {
        new_string += s[-1] + count
    } else {
        new_string += s[-1]
    }

    return new_string
}

println letter_repeats_replacement('AAAAABBDCAAADDDDBC')
