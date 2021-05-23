<template>
  <div class="container">
      <div class="row">
        <div class="col-12">
          <h3 class="my-3 text-center">Register</h3>
          <ul class="nav nav-pills nav-fill my-5">
            <li class="nav-item">
              <a :class="'nav-link '+(registerGradjanin ? 'active' : '')" href="#" @click="switchToGradjaninView">Gradjanin</a>
            </li>
            <li class="nav-item">
              <a :class="'nav-link '+(registerGradjanin ? '' : 'active')" href="#" @click="switchToSluzbenikView">Sluzbenik</a>
            </li>
          </ul>
          <div :id="idEditorGradjaninWrapper"></div>
          <div :id="idEditorSluzbenikWrapper"></div>
          <button
            class="btn btn-outline-primary btn-block my-4"
            @click="register"
          >
            <div v-if="loadingSubmit" class="spinner-border mr-2" role="status" :style="{width: '1rem', height: '1rem', 'font-size': '8px'}">
              <span class="sr-only">Loading...</span>
            </div>
            <span>Register</span>
          </button>
        </div>
      </div>
    </div>
</template>

<script>
/*eslint no-undef: "warn"*/
export default {
  name: 'Register',
  data: () => {
    return {
      loadingSubmit: false,
      registerGradjanin: true,
      idEditorGradjanin: 'idEditorGradjanin',
      idEditorGradjaninWrapper: 'idEditorGradjaninWrapper',
      idEditorSluzbenik: 'idEditorSluzbenik',
      idEditorSluzbenikWrapper: 'idEditorSluzbenikWrapper',
      gradjaninSpec: {
        onchange: function(){
          console.log("I been changed now!")
        },
        validate: function (jsElement) {
          //Validate the element
          let elementSpec = this.elements[jsElement.name];

          if (elementSpec.validate) elementSpec.validate(jsElement);

          //Cycle through the element's attributes
          for (let i = 0; i < jsElement.attributes.length; i++) {
            let jsAttribute = jsElement.attributes[i];
            let attributeSpec = elementSpec.attributes[jsAttribute.name];
            if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
          }

          //Cycle through the element's children
          for (let i = 0; i < jsElement.children.length; i++) {
            let jsChild = jsElement.children[i];
            if (jsChild.type == "element") { //if element
              this.validate(jsChild); //recursion
            }
          }
        },
        elements: {
          "gr:Gradjanin": {
            hasText: false,
            oneLiner: true
          },
          "gr:korisnicko_ime": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["gr:lozinka", "gr:podaci"],
            hasText: true
          },
          "gr:lozinka": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["gr:podaci"],
            displayValue: function(jsElement) {
              let char = ''
              for (let i = 0; i < jsElement.getText().length; i++) {
                char += '*';
              }
              return char;
            },
            hasText: true
          },
          "gr:podaci": {
            hasText: false
          },
          "util:Adresa": {
            mustBeBefore: ["util:Ime", "util:Prezime"],
            hasText: false
          },
          "util:Mesto": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["util:Ulica", "util:Broj"],
            hasText: true
          },
          "util:Ulica": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["util:Broj"],
            hasText: true
          },
          "util:Broj": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
              if (isNaN(jsElement.getText())) {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element mora biti ceo broj"
                  }
                );
              }
            },
            hasText: true
          },
          "util:Ime": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["util:Prezime"],
            hasText: true
          },
          "util:Prezime": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            hasText: true
          },
        }
      },


      sluzbenikSpec: {
        onchange: function(){
          console.log("I been changed now!")
        },
        validate: function (jsElement) {
          //Validate the element
          let elementSpec = this.elements[jsElement.name];
          if (elementSpec.validate) elementSpec.validate(jsElement);

          //Cycle through the element's attributes
          for (let i = 0; i < jsElement.attributes.length; i++) {
            let jsAttribute = jsElement.attributes[i];
            let attributeSpec = elementSpec.attributes[jsAttribute.name];
            if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
          }

          //Cycle through the element's children
          for (let i = 0; i < jsElement.children.length; i++) {
            let jsChild = jsElement.children[i];
            if (jsChild.type == "element") { //if element
              this.validate(jsChild); //recursion
            }
          }
        },
        elements: {
          "sl:Sluzbenik": {
            validate: function (jsElement) {
              if (jsElement.hasElements()) {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            hasText: false
          },
          "sl:korisnicko_ime": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["gr:lozinka", "sl:preduzece"],
            menu: [],
            attributes: {},
            hasText: true
          },
          "sl:lozinka": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            displayValue: function(jsElement) {
              let char = ''
              for (let i = 0; i < jsElement.getText().length; i++) {
                char += '*';
              }
              return char;
            },
            mustBeBefore: ["sl:preduzece"],
            hasText: true
          },
          "sl:preduzece": {
            validate: function (jsElement) {
              if (jsElement.hasElements()) {
                Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                  text: "Ovaj xml element ne moze biti prazan."
                }
                );
              }
            },
            hasText: function() {return false;},
            isReadOnly: true
          },
          "util:Adresa": {
            validate: function (jsElement) {
              if (jsElement.hasElements()) {
                Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                  text: "Ovaj xml element ne moze biti prazan."
                }
                );
              }
            },
            mustBeBefore: ["util:Naziv"],
            hasText: false
          },
          "util:Mesto": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["util:Ulica", "util:Broj"],
            hasText: true,
            displayValue: function() {
              return process.env.VUE_APP_PREDUZECE_ADRESA_MESTO
            }
          },
          "util:Ulica": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            mustBeBefore: ["util:Broj"],
            hasText: true,
            displayValue: function() {
              return process.env.VUE_APP_PREDUZECE_ADRESA_ULICA
            }
          },
          "util:Broj": {
            validate: function (jsElement) {
              if (jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
              if (isNaN(jsElement.getText())) {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element mora biti ceo broj"
                  }
                );
              }
            },
            hasText: true,
            displayValue: function() {
              return process.env.VUE_APP_PREDUZECE_ADRESA_BROJ
            }
          },
          "util:Naziv": {
            validate: function (jsElement) {
              if (!jsElement.getText() == "") {
                Xonomy.warnings.push(
                  {
                    htmlID: jsElement.htmlID,
                    text: "Ovaj xml element ne moze biti prazan."
                  }
                );
              }
            },
            hasText: true,
            displayValue: function() {
              return process.env.VUE_APP_PREDUZECE_NAZIV
            }
          }
        }
      }
    };
  },
  mounted() {
    this.switchToGradjaninView();
  },
  methods: {
    switchToGradjaninView() {
      this.registerGradjanin = true;
      
      document.getElementById(this.idEditorSluzbenikWrapper).innerHTML = '';
      
      const container = document.getElementById(this.idEditorGradjaninWrapper);
      let page = document.createElement('page');
      page.id = this.idEditorGradjanin;
      page.attributes.size = '4';
      container.appendChild(page);

      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <gr:Gradjanin xmlns:gr="http://ftn.uns.ac.rs/tim5/model/gradjanin"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <gr:korisnicko_ime></gr:korisnicko_ime>
            <gr:lozinka></gr:lozinka>
            <gr:podaci>
                <util:Adresa>
                    <util:Mesto></util:Mesto>
                    <util:Ulica></util:Ulica>
                    <util:Broj></util:Broj>
                </util:Adresa>
                <util:Ime></util:Ime>
                <util:Prezime></util:Prezime>
            </gr:podaci>
        </gr:Gradjanin>`;
      Xonomy.render(xmlString, page, {
        validate: this.gradjaninSpec.validate,
        elements: this.gradjaninSpec.elements,
        onchange: () => { this.gradjaninSpec.onchange() }
      });
      this.gradjaninSpec.onchange()
    },
    switchToSluzbenikView () {
      this.registerGradjanin = false;

      document.getElementById(this.idEditorGradjaninWrapper).innerHTML = '';
      
      const container = document.getElementById(this.idEditorSluzbenikWrapper);
      let page = document.createElement('page');
      page.id = this.idEditorGradjanin;
      page.attributes.size = '4';
      container.appendChild(page);

      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <sl:Sluzbenik xmlns:sl="http://ftn.uns.ac.rs/tim5/model/sluzbenik"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <sl:korisnicko_ime></sl:korisnicko_ime>
            <sl:lozinka></sl:lozinka>
            <sl:preduzece>
                <util:Adresa>
                    <util:Mesto></util:Mesto>
                    <util:Ulica></util:Ulica>
                    <util:Broj></util:Broj>
                </util:Adresa>
                <util:Naziv></util:Naziv>
            </sl:preduzece>
        </sl:Sluzbenik>`;
      Xonomy.render(xmlString, page, {
        validate: this.sluzbenikSpec.validate,
        elements: this.sluzbenikSpec.elements,
        onchange: () => { this.sluzbenikSpec.onchange() }
      });
      this.sluzbenikSpec.onchange();
    },
    register() {
      console.log(Xonomy.warnings)
      console.log(Xonomy.harvest());
    }
  }
}
</script>

<style>

</style>