<template>
    <v-card>
        <v-card-title>
            {{ headerTitle }}
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="12">
                        <v-text-field label="ID*" required="required" v-model="userId"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field
                            label="Password*"
                            type="password"
                            required="required"
                            v-model="userPw"></v-text-field>
                    </v-col>

                </v-row>
            </v-container>
            <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions class="justify-end mr-2 pb-4">
            <v-btn
                color="red"
                dark="dark"
                rounded="rounded"
                small="small"
                @click="$emit('hide')">
                {{ footerHideTitle }}
            </v-btn>
            <template v-if="footerSubmit">
                <v-btn
                    color="success"
                    rounded="rounded"
                    small="small"
                    @click="login()">
                    {{ footerSubmitTitle }}
                </v-btn>
            </template>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: 'login',
        props: {
            footerSubmit: {
                type: Boolean,
                default: true
            },
            headerTitle: {
                type: String,
                default: '제목'
            },
            footerSubmitTitle: {
                type: String,
                default: '저장'
            },
            footerHideTitle: {
                type: String,
                default: '닫기'
            }
        },
        data() {
            return {
                userId: '', 
                userPw: ''
            }
        },
        methods: {
             login: function () {
                // 입력 받은 데이터를 모아 하나의 객체로 생성
                let user = {
                    userId:this.userId,
                    userPw:this.userPw
                }
                console.log(user.userId);
                this.$emit('submit', user); // #하위 컴포넌트에서 상위컴포넌트로 데이터 전달
            }
        }
    }
</script>