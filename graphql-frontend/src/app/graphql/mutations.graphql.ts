import { gql } from "apollo-angular";

const CREATE_BRAND = gql`
  mutation CreateBrand($brandDto: BrandDto){
    saveBrand(brandDto: $brandDto){
      id
      name
    }
  }
`;


export {CREATE_BRAND};
