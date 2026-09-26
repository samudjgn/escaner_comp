int main() {
    int x, y;
    x = 0;
    y = 10;

    if (x <= 5 && y != 0) {
        x = x + 1;
    } else {
        y = y - 2;
    }

    while (x < 10 || y >= 5) {
        x = x + 2;
    }

    int bits = 1 << 3;
    bits = bits >> 1;
    int and_bits = bits & 3;
    int or_bits = and_bits | 8;
    int xor_bits = or_bits ^ 2;
    int not_bits = ~xor_bits;

    struct Objeto {
        int valor;
    };

    struct Objeto obj;
    obj.valor = 100;

    obj->valor = 200;

    int lista[5];
    lista[0] = 7;

    char texto = "Prueba de escaner";

    return 0;
}