<template>
    <v-card>
        <v-card-title>
            {{ headerTitle }}
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="12">
                        <v-text-field label="Email*" required="required"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field label="Password*" type="password" required="required"></v-text-field>
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
                <v-btn color="success" rounded="rounded" small="small" @click="$emit('submit')">
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
            }
        },
        data: () => (
            {footerHideTitle: '닫기', defaultBodyContent: 'body slot 영역을 작성해주세요.'}
        )
    }
</script>
