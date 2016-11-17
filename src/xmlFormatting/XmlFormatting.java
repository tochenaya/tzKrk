package xmlFormatting;

import java.util.*;

public class XmlFormatting {


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Member>> projectMap = new HashMap<>();
        String project = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("<project name")) {
                int start = line.indexOf("\"") + 1;
                int end = line.indexOf("\"", start);
                project = line.substring(start, end);
            }
            if (line.contains("<member")) {
                int startR = line.indexOf("\"") + 1;
                int endR = line.indexOf("\"", startR);
                int startN = line.indexOf("\"", endR + 1) + 1;
                int endN = line.indexOf("\"", startN);
                String role = line.substring(startR, endR);
                String name = line.substring(startN, endN);

                if (projectMap.containsKey(project)) {
                    projectMap.get(project).add(new Member(role, name));
                } else {
                    List<Member> members = new ArrayList<>();
                    members.add(new Member(role, name));
                    projectMap.put(project, members);
                }
            }
        }

        Map<String, Set<Role>> memberMap = new TreeMap<>();

        projectMap.forEach((projectKey, memberList) -> {
            memberList.forEach(member -> {
                if (memberMap.containsKey(member.name)) {
                    memberMap.get(member.name).add(new Role(member.role, projectKey));
                } else {
                    Set<Role> roles = new TreeSet<Role>((Role r1, Role r2) -> {
                        if(r1.project.equals(r2.project)) return r1.name.compareTo(r2.name);
                        return  r1.project.compareTo(r2.project);
                    });
                    roles.add(new Role(member.role, projectKey));
                    memberMap.put(member.name, roles);
                }
            });
        });

        System.out.println("<members>");
        memberMap.forEach((member, roleSet) -> {
            System.out.println("    <member name=\"" + member + "\"/>");
            roleSet.forEach(role -> {
                System.out.println("        <role name=\"" + role.name + "\" name=\"" + role.project + "\"/>");
            });
            System.out.println("    </member>");
        });
        System.out.print("</members>");
    }

    static private class Member {
        Member(String role, String name) {
            this.role = role;
            this.name = name;
        }

        String role;
        String name;
    }

    static private class Role {
        Role(String name, String project) {
            this.name = name;
            this.project = project;
        }

        String name;
        String project;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Role role = (Role) o;

            if (name != null ? !name.equals(role.name) : role.name != null) return false;
            return project != null ? project.equals(role.project) : role.project == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (project != null ? project.hashCode() : 0);
            return result;
        }
    }
}
