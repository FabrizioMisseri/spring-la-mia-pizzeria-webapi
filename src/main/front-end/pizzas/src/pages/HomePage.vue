<script>
import { store } from '../store';
import axios from 'axios';

export default {
    name: 'HomePage',

    data() {
        return {
            store,
            arrayPizzas: [],
        }
    },

    created() {
        this.axiosCall(this.store.query);
    },

    methods: {
        axiosCall(query) {
            axios.get(`${this.store.baseApiUrl}?q=${query}`).then((resp) => {
                this.arrayPizzas = resp.data;
                console.log(this.arrayPizzas);
            });
        },

        axiosDelete(id) {
            axios.delete(`${this.store.baseApiUrl}/${id}`).then((resp) => {
                console.log(resp);
                this.axiosCall(this.store.query);
            });
        },
    }
}
</script>

<template>
    <div class="container py-5">
        <div class="row mb-5">
            <div class="col-6">
                <input id="search-bar" class="form-control" type="text" v-model="this.store.query"
                    @keyup.enter="axiosCall(this.store.query)" placeholder="cerca una pizza">
            </div>

            <div class="col-6 text-end">
                <RouterLink :to="{ name: 'second' }" class="btn btn-warning">Iserisci una pizza</RouterLink>
            </div>
        </div>
        <div class="row">
            <div class="card mb-4 py-3" v-for="(pizza, index) in arrayPizzas" :key="index">

                <ul>
                    <li>
                        <h5>{{ pizza.id }}</h5>
                    </li>
                    <li>
                        <h4>{{ pizza.name }}</h4>
                    </li>
                    <li>
                        <span>{{ pizza.price }}€</span>
                    </li>
                    <li>
                        <p>
                            {{ pizza.description }}.
                        </p>
                    </li>
                    <li>
                        <div class="btn btn-warning" @click="axiosDelete(pizza.id)">
                            delete-pizza
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
ul {
    list-style: none;
}

.card {
    .sensitive {
        text-decoration: none;
        color: inherit;
    }
}

#search-bar {
    max-width: 300px;
}
</style>