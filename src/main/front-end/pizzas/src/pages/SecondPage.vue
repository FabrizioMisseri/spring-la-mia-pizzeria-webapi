<script>
import axios from 'axios';
import { store } from '../store';

export default {
    name: 'SecondPage',

    data() {
        return {
            store,
            pizzaName: "",
            pizzaDescription: "",
            pizzaPrice: "",
        }
    },

    methods: {
        //         {
        //     "ingredients": null,
        //     "id": 22,
        //     "name": "nuova_pizza_edited",
        //     "price": 7.0,
        //     "description": "pomodoro, mozzarella"
        // }
        axiosPostCreate(name, price, desc) {
            axios.post(`${this.store.baseApiUrl}`, {
                name: name,
                price: price,
                description: desc,
            }).then((resp) => {
                console.log(resp);

            });
            this.pizzaName = "";
            this.pizzaDescription = "";
            this.pizzaPrice = "";
        }
    }
}
</script>

<template>
    <div class="container">
        <div class="row pt-5">
            <div class="col-10 offset-1 mb-5">
                <RouterLink :to="{ name: 'home' }" class="btn btn-warning">torna alla homePage</RouterLink>
            </div>
            <div class="col-10 offset-1">
                <div class="card p-3">
                    <form class="form-group" action="">
                        <input v-model="pizzaName" type="text" class="form-control mb-2">
                        <textarea v-model="pizzaDescription" class="form-control mb-2"></textarea>
                        <input v-model="pizzaPrice" type="number" class="form-control mb-2">
                        <button @click.prevent="axiosPostCreate(pizzaName, pizzaPrice, pizzaDescription)"
                            class="btn btn-warning">Salva</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped></style>