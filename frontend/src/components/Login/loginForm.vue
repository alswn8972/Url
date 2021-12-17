    <template>
    <div class="content">
        <div class="md-layout">
        <div class="md-layout-item md-medium-size-100 md-size-100">
            <form novalidate class="md-layout" @submit.prevent="validateUser">
                <md-card>
                <md-card-header data-background-color="blue">
                    <h4 class="title">Login</h4>
                    <p class="category">더 많은 기능을 이용하시려면 로그인을 해주세요 :D</p>
                </md-card-header>
                    <md-card-content>
                        <div class="md-layout md-gutter">
                            <div class="md-layout-item md-small-size-100 md-size-100">
                            <md-field :class="getValidationClass('userId')">
                                <label for="userId">아이디</label>
                                <md-input name="userId" id="userId" autocomplete="given-name" v-model="form.userId" :disabled="sending" />
                                <span class="md-error" v-if="!$v.form.userId.required">아이디는 필수 입력 값입니다.</span>
                            </md-field>
                            </div>

                            <div class="md-layout-item md-small-size-100 md-size-100">
                            <md-field :class="getValidationClass('userPw')">
                                <label for="userPw">비밀번호</label>
                                <md-input name="luserPw" id="userPw" autocomplete="family-name" v-model="form.userPw" :disabled="sending" />
                                <span class="md-error" v-if="!$v.form.userPw.required">비밀번호는 필수 입력 값입니다.</span>

                            </md-field>
                            </div>
                        </div>  
                        <div class="md-layout-item md-size-100 text-right">
                            <md-button type="submit" class="md-primary" :disabled="sending">로그인</md-button>
                        </div>
                
                    </md-card-content>
                </md-card>

                </form>
        </div>
        </div>
    </div>
</template>
<script>
import { validationMixin } from 'vuelidate'
import {
    required,
} from 'vuelidate/lib/validators'
import {mapActions} from "vuex"
export default {
    name: 'FormValidation',
    mixins: [validationMixin],
    data: () => ({
        form: {
            userId: null,
            userPw: null,
        },
        userSaved: false,
        sending: false,
        lastUser: null
    }),
    validations: {
        form: {
            userId: {
            required,
            },
            userPw: {
            required,
            },

        }
    },
    methods: {
    ...mapActions("user", ["requestLogin"]),
        getValidationClass (fieldName) {
            const field = this.$v.form[fieldName]

            if (field) {
            return {
                'md-invalid': field.$invalid && field.$dirty
            }
            }
        },
        validateUser () {
            this.$v.$touch()

            if (!this.$v.$invalid) {
                this.requestLogin(this.form)
            }
        }
    }
}
</script>